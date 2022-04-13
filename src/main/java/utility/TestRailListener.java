package utility;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import utility.QAAnnotations.TestCaseInfo;
import static utility.DataCollectionUtil.getSuiteString;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.PrintWriter;

import org.json.simple.JSONObject;

public class TestRailListener 
implements ITestListener, ISuiteListener {

	private static final String PROP_TESTRAIL_USER = "agonzalez@arexdata.com";
	private static final String PROP_TESTRAIL_PASSWORD = "Arexdata1!";
	private static final String PROP_TESTRAIL_URL = "https://arexdata.testrail.io/";

	private static final String PROP_PROJECTNAME = "env.TestrailProjectName";
	private static final String PROP_TESTRUNNAME = "env.TestrailRunName";
	private static final String PROP_RUNID = "env.RunId";
	private static final String PROP_UPDATETESTRAIL = "env.UpdateTestRailResults";
	private static final String PROP_DEBUG = "env.TestRailDebug";

	private static final String DEFAULT_TESTRAIL_USER = "agonzalez@arexdata.com";
	private static final String DEFAULT_TESTRAIL_PASSWORD = "Arexdata1!";
	private static final String DEFAULT_TESTRAIL_URL = "https://arexdata.testrail.io/";

	private static final int TEST_RAIL_STATUS_SUCCESS = 1;
	private static final int TEST_RAIL_STATUS_SKIP = 2;
	private static final int TEST_RAIL_STATUS_FAILURE = 5;
	PropertyReader propertyReader=new PropertyReader();
	private TestRailHandler th = null;
	private String runid= propertyReader.readProperty("TestrailRunId");;
	private boolean debug;

	private String logfn = "testResults.json";
	private FileWriter log_fh;
	enum testStatus
	{
		SUCCESS, FAILURE, SKIPPED;
	}
	public boolean isTestRailToBeUpdated(ISuite suite) {

		Boolean isTestRailToBeUpdated = (Boolean) suite.getAttribute("UpdateTestRails");

		if (isTestRailToBeUpdated == null) {
			SuiteRunProperties suiteProps = DataCollectionUtil.getSuiteProps(suite);
			String s = suiteProps.getProperty(PROP_UPDATETESTRAIL);
			isTestRailToBeUpdated = (s != null) && ("true".equals(s) || "True".equals(s));
			suite.setAttribute("UpdateTestRails", isTestRailToBeUpdated);
		}
		
		return isTestRailToBeUpdated;
	}
	
	@Override
	public void onStart(ISuite suite) {
		DataCollectionUtil.initialize(suite);
		
		debug = "true".equals(getSuiteString(suite, PROP_DEBUG, "false"));
	
		// debug = true;
		if (debug || isTestRailToBeUpdated(suite)) {
			try {
				String user = getSuiteString(suite, PROP_TESTRAIL_USER, DEFAULT_TESTRAIL_USER);
				String password = getSuiteString(suite, PROP_TESTRAIL_PASSWORD, DEFAULT_TESTRAIL_PASSWORD);
				String url = getSuiteString(suite, PROP_TESTRAIL_URL, DEFAULT_TESTRAIL_URL);
				
				String projectName = getSuiteString(suite, PROP_PROJECTNAME);
				String testRunName = getSuiteString(suite, PROP_TESTRUNNAME);
				runid = getSuiteString(suite, PROP_RUNID);
				
				th = new TestRailHandler(user, password, url);
				
// 				runid = getRunId(projectName, testRunName);
				
				System.err.println(String.format("TestRail listener initialied: %s(%s) - %s", user, password, url));
				System.err.println(String.format("Project: %s Run: %s(%s)", projectName, testRunName, runid));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {

			log_fh = new FileWriter(logfn);
			System.out.println("Opened file for writing " + logfn + ".............................");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Long getRunId(String projectName, String testRunName) throws Exception {
	 if (debug) {
		int debugRunId = 42;
		System.err.println(String.format("DEBUG: getRunId(%s, %s) returning %d",
				 projectName, testRunName, debugRunId));
		return new Long(debugRunId);
	 }
	 return th.getRunIdByTestRunName(projectName, testRunName);
	}

	private void writetolog(ITestResult result, testStatus Status) {
		JSONObject obj = new JSONObject();
		obj.put("TestName", result.getMethod().getMethodName());
		obj.put("SuiteName", result.getTestContext().getSuite().getName());
		obj.put("StartTime", result.getStartMillis());
		obj.put("EndTime", result.getEndMillis());
		obj.put("Status", Status.ordinal());
		obj.put("FullName", result.getTestContext().getSuite().getName() + "." + result.getMethod().getMethodName());

		ITestNGMethod method = result.getMethod();
		TestCaseInfo info = TestRailUtil.getTestCaseInfoAnnotation(method.getRealClass(), method.getMethodName());
		if ((info != null) && (info.testCaseID() != null) ) {
		    obj.put("testCaseID", cleanup(info.testCaseID()));
		}
		else {
		    obj.put("testCaseID", null);
		}
		obj.put("misc", null);


		if (Status == testStatus.FAILURE || Status == testStatus.SKIPPED) {
			Throwable th = result.getThrowable();

			if (th != null) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				th.printStackTrace(pw);
				obj.put("Error",sw.toString());
			}
			else{
				obj.put("Error", "");
			}
		}

		try {
			log_fh.write(obj.toJSONString());
			log_fh.write("\r\n");
		} catch(Exception e){
			System.out.println(e);
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		updateResultInTestRail(result,TEST_RAIL_STATUS_SUCCESS);
		writetolog(result, testStatus.SUCCESS);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		updateResultInTestRail(result, TEST_RAIL_STATUS_SKIP);
		writetolog(result, testStatus.SKIPPED);
	}


	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Failed.............................");
		updateResultInTestRail(result, TEST_RAIL_STATUS_FAILURE);
		writetolog(result, testStatus.FAILURE);
	}
	

	public void updateResultInTestRail(ITestResult result, int testRailStatus) {
		if (th != null) {
			try {
				ITestNGMethod method = result.getMethod();
				TestCaseInfo info = TestRailUtil.getTestCaseInfoAnnotation(method.getRealClass(), method.getMethodName());
				if (info != null) {
					System.out.println(info);
					String caseID = cleanup(info.testCaseID());
					System.out.println(caseID);
					updateResultToTestRail(caseID, testRailStatus);
				} else {
					updateResultToTestRail(
							result.getTestClass().getName(), 
							result.getMethod().getMethodName(),
							testRailStatus);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private String cleanup(String testCaseID) {
		if (testCaseID == null) return null; // should never happen.  required in annotation.
		
		if (testCaseID.isEmpty()) {
			System.err.println("Empty testCaseID encountered");
			return "";
		}
		
		StringBuffer b = new StringBuffer(testCaseID.length());
		for (int i = 0; i < testCaseID.length(); i++) {
			char c = testCaseID.charAt(i);
			if (Character.isDigit(c)) {
				b.append(c);
			}
		}
		
		if (b.length() != testCaseID.length()) {
			System.err.println("'" + testCaseID + "' converted to '" + b.toString() + "'");
		}
		
		return b.toString();
	}

	private void updateResultToTestRail(String name, String methodName, int testRailStatus) {
		if (debug) {
			System.err.println(String.format("updateResultToTestRail(%s, %s, %s) - by class/method name",
					name, methodName, testRailStatus)
			);
			return;
		}
		//th.updateResultToTestRail(testRailStatus, caseID, runid);
	}

	public void updateResultToTestRail(String caseID, int testRailStatus) throws Exception {
		if (debug) {
			System.err.println(String.format("updateResultToTestRail(%s, %s, %s)",
					testRailStatus, caseID, runid)
			);
			return;
		}
		th.updateResultToTestRail(testRailStatus, caseID, runid);
	}

	@Override
	public void onTestStart(ITestResult arg0) {
	}
	
	@Override
	public void onFinish(ITestContext arg0) {
	}

	@Override
	public void onStart(ITestContext arg0) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	@Override
	public void onFinish(ISuite suite) {
		try {
			System.out.println("Closing file:" + logfn);
			log_fh.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
