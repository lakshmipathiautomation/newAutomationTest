package utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import utility.SuiteRunProperties;
import utility.TestRailHandler;

import org.testng.IInvokedMethod;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.collections.Lists;
import org.testng.internal.Utils;
import org.testng.xml.XmlSuite;

import utility.QAAnnotations.TestCaseInfo;

public class TestNGCustomReportListener implements ITestListener, IReporter, ISuiteListener {

	private static final String CLASS_UNEXPECTED = "unexpected";
	private static final String CLASS_EXPECTED = "expected";
	private static final String COLOR_WARN = "#FFFFBB";
	private static final Object COLOR_ERROR = "#FFBBBB";
	private static final String CLASS_PASSED = "passed";
	private static final String CLASS_SKIPPED = "skipped";
	private static final String CLASS_FAILED = "failed";
	public static final String TESTRUN_PROP = "arexdata.testsuite.props";
	
	private PrintWriter writer;
	private int m_row;
	private Integer m_testIndex;
	private String reportTitle= "Execution Report";
	private String reportFileName = "selenium-automation-report.html";
	PropertyReader propertyReader=new PropertyReader();

	public String browserType = propertyReader.readProperty("browser");
	public String projectName = propertyReader.readProperty("TestrailProjectName");
	public String testRunName = propertyReader.readProperty("TestrailRunName");
	public String config = propertyReader.readProperty("UpdateTestRailResults");
	TestRailHandler th=null;
	String caseID="" ;
	String runid = propertyReader.readProperty("TestrailRunId");
	
	/** Creates summary of the run */
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outdir) {
		try {
			writer = createWriter(outdir);
		} catch (IOException e) {
			System.err.println("Unable to create output file");
			e.printStackTrace();
			return;
		}
		
		startHtml(writer);
		writeReportTitle(reportTitle, suites);
		generateSuiteSummaryReport(suites);
		generateMethodSummaryReport(suites);
		generateMethodDetailReport(suites);
		endHtml(writer);
		writer.flush();
		writer.close();
	}
	@Override
	public void onTestFailure(ITestResult result)  {
		System.out.println("Failed.............................");
	    updateResultInTestRail(result);
	}
	
	protected PrintWriter createWriter(String outdir) throws IOException {
		new File(outdir).mkdirs();
		return new PrintWriter(new BufferedWriter(new FileWriter(new File(outdir, reportFileName))));
	}
	 @Override
	    public void onFinish(ITestContext arg0) {
                      
	    }
	 
	    @Override
	    public void onStart(ITestContext arg0) {
	        // TODO Auto-generated method stub
	        
	    }
	 
	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	        // TODO Auto-generated method stub
	        
	    }
	 
	
	 
	    @Override
	    public void onTestSkipped(ITestResult result) {

	    	updateResultInTestRail(result);
	    }
	    
	    public void updateResultInTestRail(ITestResult result) {
	    
	    if(config.contains("true") || config.contains("True")){
	    	
			try{
				 th= new TestRailHandler("agonzalez@arexdata.com","Arexdata1!","https://arexdata.testrail.io/");
				 String getRunMethodName = result.getMethod().getMethodName();

				 TestCaseInfo info =(TestCaseInfo) result.getMethod().getRealClass().getMethod(getRunMethodName).getAnnotation(TestCaseInfo.class);
				 System.out.println(info);
				 caseID =  cleanup(info.testCaseID());
				 System.out.println(caseID);
				} catch(Exception e){}
				
			   if (ITestResult.FAILURE == result.getStatus()) {
				   try{
					   th.updateResultToTestRail(5, caseID, runid); // // For Failed
					   System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
				   }	catch(Exception e){
					   System.out.println(e);
								
			   	} 
			   }
				if (ITestResult.SKIP == result.getStatus()) {
					try{
						th.updateResultToTestRail(3, caseID, runid); // // For Skip
						System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
					}catch(Exception e){System.out.println(e);}
					
				} 			
				
				if (ITestResult.SUCCESS == result.getStatus()) {
					try{
						th.updateResultToTestRail(1, caseID, runid); // // For Success
						System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
					}catch(Exception e){ System.out.println(e);}
					
				} 
		   }
	    
	  }  
	    @Override
	    public void onTestStart(ITestResult arg0) {
	        // TODO Auto-generated method stub
	    	System.out.println(("*** Running test method " + arg0.getMethod().getMethodName() + "..."));
	    }
	 
	    @Override
	    public void onTestSuccess(ITestResult result) {
		
	    	updateResultInTestRail(result);
	        
	    }
	    


	/**
	 * Creates a table showing the highlights of each test method with links to
	 * the method details
	 */
	protected void generateMethodSummaryReport(List<ISuite> suites) {
		//m_methodIndex = 0;
		
		int testIndex = 1;
		for (ISuite suite : suites) {
			startResultSummaryTable("methodOverview");
			Map<String, ISuiteResult> r = suite.getResults();
			for (ISuiteResult r2 : r.values()) {
				ITestContext testContext = r2.getTestContext();
				String testName = testContext.getName();
				m_testIndex = testIndex;
				resultSummary(suite, testContext.getFailedConfigurations(), testName, "failed", " (configuration methods)");
				resultSummary(suite, testContext.getFailedTests(), testName, "failed", "");
				resultSummary(suite, testContext.getSkippedConfigurations(), testName, "skipped", " (configuration methods)");
				resultSummary(suite, testContext.getSkippedTests(), testName, "skipped", "");
				resultSummary(suite, testContext.getPassedTests(), testName, "passed", "");
				
				testIndex++;
			}
		}
		writer.println("</table>");
	}
   
	/** Creates a section showing known results for each method */
	protected void generateMethodDetailReport(List<ISuite> suites) {
		// m_methodIndex = 0;
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> r = suite.getResults();
			for (ISuiteResult r2 : r.values()) {
				ITestContext testContext = r2.getTestContext();
				if (r.values().size() > 0) {
					writer.println("<h1><b>" + testContext.getName() + "</b></h1>");
				}
				resultDetail(testContext.getFailedConfigurations(),"#FFBBBB");
				resultDetail(testContext.getFailedTests(),"#FFBBBB");
				resultDetail(testContext.getSkippedConfigurations(),"#FFFFBB");
				resultDetail(testContext.getSkippedTests(),"#FFFFBB");
				resultDetail(testContext.getPassedTests(),"lightgreen");
				
			}
		}
	}

	/**
	 * @param tests
	 */
	private void resultSummary(ISuite suite, IResultMap tests, String testname,
			String style, String details) {
		
		if (tests.getAllResults().size() > 0) {
			StringBuffer buff = new StringBuffer();
			String lastClassName = "";
			int methodCount = 0;
			int testCount = 0;
			for (ITestNGMethod method : getMethodSet(tests, suite)) {
				m_row += 1;
				ITestClass testClass = method.getTestClass();
				String className = testClass.getName();
				if (methodCount == 0) {
					String id = (m_testIndex == null ? null : "t"
							+ Integer.toString(m_testIndex));
					titleRow(testname + " &#8212; " + style + details, 5, id);
					m_testIndex = null;
				}
				if (!className.equalsIgnoreCase(lastClassName)) { 
					// new class, need write out lastClass if there was one.
					if (methodCount > 0) {
						testCount += 1;
						writer.print("<tr class=" + style
								+ (testCount % 2 == 0 ? "even" : "odd") + ">"
								+ "<td");
						if (methodCount > 1) {
							writer.print(" rowspan=" + methodCount + "");
						}
						writer.println(">" + lastClassName + "</td>" + buff);
					}
					methodCount = 0;
					buff.setLength(0);
					lastClassName = className;
				}
				Set<ITestResult> resultSet = tests.getResults(method);
				long end = Long.MIN_VALUE;
				long start = Long.MAX_VALUE;
				long startMS=0;
				String firstLine="";
				
				for (ITestResult testResult : tests.getResults(method)) {
					if (testResult.getEndMillis() > end) {
						end = testResult.getEndMillis()/1000;
					}
					if (testResult.getStartMillis() < start) {
						startMS = testResult.getStartMillis();
						start =startMS/1000;
					}
					
					Throwable exception=testResult.getThrowable();
					boolean hasThrowable = exception != null;
					if(hasThrowable){
//						String str = Utils.stackTrace(exception, true)[0];
//						Scanner scanner = new Scanner(str);
//						firstLine = scanner.nextLine();
					}
				}
				DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
				Calendar calendar = Calendar.getInstance();
			    calendar.setTimeInMillis(startMS);
			     
				methodCount += 1;
				if (methodCount > 1) {
					buff.append("<tr class=" + style
							+ (testCount % 2 == 0 ? "odd" : "even") + ">");
				}
				String description = method.getDescription();
				String testInstanceName = resultSet
						.toArray(new ITestResult[] {})[0].getTestName();
				buff.append("<td><a href=\"#m_"
						+ method.getMethodName()
						+ "\">"
						+ qualifiedName(method)
						+ " "
						+ (description != null && description.length() > 0 ? "("
								+ description + ")"
								: "")
								+ "</a>"
								+ (null == testInstanceName ? "" : "<br>("
										+ testInstanceName + ")") + "</td>"
										+ "<td class=numi style=text-align:left;padding-right:2em>" + firstLine+"<br/></td>"
										+ "<td style=text-align:right>" + formatter.format(calendar.getTime()) + "</td>" + "<td class=numi>"
										+ timeConversion(end - start) + "</td>" + "</tr>");
				
			}
			if (methodCount > 0) {
				testCount += 1;
				writer.print("<tr class=" + style+(testCount % 2 == 0 ? "even" : "odd") + ">" + "<td");
				if (methodCount > 1) {
					writer.print(" rowspan=" + methodCount + "");
				}
				writer.println(">" + lastClassName + "</td>" + buff);
			}
		}
	}
    
	
	private String timeConversion(long seconds) {

	    final int MINUTES_IN_AN_HOUR = 60;
	    final int SECONDS_IN_A_MINUTE = 60;

	    int minutes = (int) (seconds / SECONDS_IN_A_MINUTE);
	    seconds -= minutes * SECONDS_IN_A_MINUTE;

	    int hours = minutes / MINUTES_IN_AN_HOUR;
	    minutes -= hours * MINUTES_IN_AN_HOUR;

	    return prefixZeroToDigit(hours) + ":" + prefixZeroToDigit(minutes) + ":" + prefixZeroToDigit((int)seconds);
	}
	
	private String prefixZeroToDigit(int num){
		int number=num;
		if(number<=9){
			String sNumber="0"+number;
			return sNumber;
		}
		else
			return ""+number;
		
	}
	
	/** Starts and defines columns result summary table */
	private void startResultSummaryTable(String style) {
		tableStart(style, "summary");
		writer.println("<tr><th bgcolor=\"#FFE4C4\">Test Class</th>"
				+ "<th bgcolor=\"#FFE4C4\">Test Cases/Method Name</th><th bgcolor=\"#FFE4C4\">Exception Information</th><th bgcolor=\"#FFE4C4\">Start Time<br/>(hh:mm:ss)</th><th bgcolor=\"#FFE4C4\">Execution Time<br/>(hh:mm:ss)</th></tr>");
		m_row = 0;
	}

	private String qualifiedName(ITestNGMethod method) {
		StringBuilder addon = new StringBuilder();
		String[] groups = method.getGroups();
		int length = groups.length;
		if (length > 0 && !"basic".equalsIgnoreCase(groups[0])) {
			addon.append("(");
			for (int i = 0; i < length; i++) {
				if (i > 0) {
					addon.append(", ");
				}
				addon.append(groups[i]);
			}
			addon.append(")");
		}

		return "<b>" + method.getMethodName() + "</b> " + addon;
	}

	private void resultDetail(IResultMap tests, String color) {
		Set<ITestResult> testResults=tests.getAllResults();
		List<ITestResult> testResultsList = new ArrayList<ITestResult>(testResults);
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		System.setProperty("java.util.Collections.useLegacyMergeSort", "true");
		Collections.sort(testResultsList, new TestResultsSorter());
		for (ITestResult result : testResultsList) {
			ITestNGMethod method = result.getMethod();
			String cname = method.getTestClass().getName();
			writer.println("<h2 bgcolor=\""+color + "\", id=\"m_" + method.getMethodName() + "\">" + cname + ":"
					+ method.getMethodName() + "</h2>");
			Set<ITestResult> resultSet = tests.getResults(method);
			generateResult(result, method, resultSet.size());
			writer.println("<p class=totop><a href=#summary>back to summary</a></p>");

		}
	}

	private void generateResult(ITestResult ans, ITestNGMethod method,
			int resultSetSize) {
		Object[] parameters = ans.getParameters();
		boolean hasParameters = parameters != null && parameters.length > 0;
		if (hasParameters) {
			tableStart("result", null);
			writer.print("<tr class=param>");
			for (int x = 1; x <= parameters.length; x++) {
				writer.print("<th>Param." + x + "</th>");
			}
			writer.println("</tr>");
			writer.print("<tr class=param stripe>");
			for (Object p : parameters) {
				writer.println("<td>" + Utils.escapeHtml(Utils.toString(p))
						+ "</td>");
			}
			writer.println("</tr>");
		}
		List<String> msgs = Reporter.getOutput(ans);
		boolean hasReporterOutput = msgs.size() > 0;
		Throwable exception = ans.getThrowable();
		boolean hasThrowable = exception != null;
		if (hasReporterOutput || hasThrowable) {
			if (hasParameters) {
				writer.print("<tr><td");
				if (parameters.length > 1) {
					writer.print(" colspan=" + parameters.length + "");
				}
				writer.println(">");
			} else {
				writer.println("<div>");
			}
			if (hasReporterOutput) {
				if (hasThrowable) {
					writer.println("<h3>Test Messages</h3>");
				}
				writer.println("<pre>");
				writer.println(msgs.stream().collect(Collectors.joining("\n")));
				writer.println("</pre>");
			}
			if (hasThrowable) {
				boolean wantsMinimalOutput = ans.getStatus() == ITestResult.SUCCESS;
				if (hasReporterOutput) {
					writer.println("<h3>"
							+ (wantsMinimalOutput ? "Expected Exception"
									: "Failure") + "</h3>");
				}
				generateExceptionReport(exception, method);
			}
			if (hasParameters) {
				writer.println("</td></tr>");
			} else {
				writer.println("</div>");
			}
		}
		if (hasParameters) {
			writer.println("</table>");
		}
	}

	protected void generateExceptionReport(Throwable exception, ITestNGMethod method) {
		writer.print("<div class=stacktrace>");
//		writer.print(Utils.stackTrace(exception, true)[0]);
		writer.println("</div>");
	}

	/**
	 * Since the methods will be sorted chronologically, we want to return the
	 * ITestNGMethod from the invoked methods.
	 */
	private Collection<ITestNGMethod> getMethodSet(IResultMap tests, ISuite suite) {
		
		List<IInvokedMethod> r = Lists.newArrayList();
		List<IInvokedMethod> invokedMethods = suite.getAllInvokedMethods();
		for (IInvokedMethod im : invokedMethods) {
			if (tests.getAllMethods().contains(im.getTestMethod())) {
				r.add(im);
			}
		}
		
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		System.setProperty("java.util.Collections.useLegacyMergeSort", "true");
		Collections.sort(r,new TestSorter());
		List<ITestNGMethod> result = Lists.newArrayList();
		
		// Add all the invoked methods
		for (IInvokedMethod m : r) {
			for (ITestNGMethod temp : result) {
				if (!temp.equals(m.getTestMethod()))
					result.add(m.getTestMethod());
			}
		}
		
		// Add all the methods that weren't invoked (e.g. skipped) that we
		// haven't added yet
		Collection<ITestNGMethod> allMethodsCollection=tests.getAllMethods();
		List<ITestNGMethod> allMethods=new ArrayList<ITestNGMethod>(allMethodsCollection);
		Collections.sort(allMethods, new TestMethodSorter());
		
		for (ITestNGMethod m : allMethods) {
			if (!result.contains(m)) {
				result.add(m);
			}
		}
		return result;
	}

	@SuppressWarnings("unused")
	public void generateSuiteSummaryReport(List<ISuite> suites) {
		tableStart("testOverview", null);
		writer.print("<tr>");
		tableColumnStart("Test Area/Feature Name");
		tableColumnStart("# Passed");
		tableColumnStart("# Skipped");
		tableColumnStart("# Failed");
		tableColumnStart("# Total");
		//tableColumnStart("Browser");
		tableColumnStart("Start<br/>Time<br/>(hh:mm:ss)");
		tableColumnStart("End<br/>Time<br/>(hh:mm:ss)");
		tableColumnStart("Total<br/>Time<br/>(hh:mm:ss)");
		tableColumnStart("Included<br/>Groups");
		tableColumnStart("Excluded<br/>Groups");

		writer.println("</tr>");
		NumberFormat formatter = new DecimalFormat("#,##0.0");
		int qty_tests = 0;
		int qty_pass_m = 0;
		int qty_pass_s = 0;
		int qty_skip = 0;
		
		long time_start = Long.MAX_VALUE;
		int qty_fail = 0;
		
		long time_end = Long.MIN_VALUE;
		m_testIndex = 1;
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> tests = suite.getResults();
			for (ISuiteResult r : tests.values()) {
				qty_tests += 1;
				ITestContext overview = r.getTestContext();
				startSummaryRow(overview.getName());
				int q = overview.getPassedTests().size();
				qty_pass_m += q;
				summaryCell(q, Integer.MAX_VALUE,CLASS_PASSED);
				q = overview.getSkippedTests().size();
				qty_skip += q;
				summaryCell(q, 0,CLASS_SKIPPED);
				q = overview.getFailedTests().size();
				qty_fail += q;
				summaryCell(q, 0,CLASS_FAILED);
				
				int total = overview.getPassedTests().size() + overview.getSkippedTests().size() +overview.getFailedTests().size();
				//int total = qty_pass_m + qty_pass_s + qty_skip + qty_fail;
				summaryCell(total, Integer.MAX_VALUE,"");
				
				// Write OS and Browser
				/*summaryCell(browserType, true,"");
				writer.println("</td>");
				*/			
				
				SimpleDateFormat summaryFormat = new SimpleDateFormat("hh:mm:ss a");
				summaryCell(summaryFormat.format(overview.getStartDate()),true,"");				
				writer.println("</td>");
				
				summaryCell(summaryFormat.format(overview.getEndDate()),true,"");
				writer.println("</td>");

				time_start = Math.min(overview.getStartDate().getTime(), time_start);
				time_end = Math.max(overview.getEndDate().getTime(), time_end);
				summaryCell(timeConversion((overview.getEndDate().getTime() - overview.getStartDate().getTime()) / 1000), true,"");
				
				summaryCell(overview.getIncludedGroups());
				summaryCell(overview.getExcludedGroups());
				writer.println("</tr>");
				m_testIndex++;
			}
		}
		if (qty_tests > 1) {
			writer.println("<tr class=total><td>Total</td>");
			summaryCell(qty_pass_m, Integer.MAX_VALUE,CLASS_PASSED);
			summaryCell(qty_skip, 0, CLASS_SKIPPED);
			summaryCell(qty_fail, 0, CLASS_FAILED);
			summaryCell(qty_pass_m+qty_skip+qty_fail, Integer.MAX_VALUE,"");
			summaryCell(" ", true,"");
			summaryCell(" ", true,"");
			summaryCell(timeConversion(((time_end - time_start) / 1000)), true,"");
			writer.println("<td colspan=3>&nbsp;</td></tr>");
		}
		writer.println("</table>");
	}
    
	
	private void summaryCell(String[] val) {
		StringBuffer b = new StringBuffer();
		for (String v : val) {
			b.append(v + " ");
		}
		summaryCell(b.toString(), true,"");
	}

	private void summaryCell(String v, boolean isgood, String clss) {
		StringBuilder sb = new StringBuilder("<td class=\"numi ");
		if ((clss != null) && !clss.isEmpty()) {
			sb.append(clss).append(' ');
		}
		sb.append(isgood ? CLASS_EXPECTED : CLASS_UNEXPECTED)
		.append("\">").append(v).append("</td>");
		writer.println(sb.toString());
	}

	private void startSummaryRow(String label) {
		m_row += 1;
		writer.print("<tr"
				+ (m_row % 2 == 0 ? " class=stripe" : "")
				+ "><td class=\"summaryrow\"><a href=\"#t"
				+ m_testIndex + "\"><b>" + label + "</b></a>" + "</td>");
		
	}
	
	private void summaryCell(int v, int maxexpected, String clss) {
		summaryCell(String.valueOf(v), v <= maxexpected, clss);
	}

	private void tableStart(String cssclass, String id) {
		writer.println("<table cellspacing=0 cellpadding=0"
				+ (cssclass != null ? " class=" + cssclass + ""
						: " style=padding-bottom:2em")
						+ (id != null ? " id=" + id + "" : "") + ">");
		m_row = 0;
	}

	private void tableColumnStart(String label) {
		writer.print("<th bgcolor=\"#FFE4C4\">" + label + "</th>");
	}

	private void titleRow(String label, int cq, String id) {
		writer.print("<tr");
		if (id != null) {
			writer.print(" id=" + id + "");
		}
		writer.println("><th colspan=" + cq + ">" + label + "</th></tr>");
		m_row = 0;
	}

	protected void writeReportTitle(String title, List<ISuite> suites) {
		String suiteName ="";
		SuiteRunProperties props = null;
		for (ISuite suite : suites) {
			if (suites.size() >= 1) {
				suiteName = suite.getName();
				props = (SuiteRunProperties) suite.getAttribute(TESTRUN_PROP);
			}
		}

		String browser = props == null   ? "unknown" : (props.getProperty("browser.browserName", browserType) + " (" + props.getProperty("browser.platform", "N/A") + ')');
	
		writer.print(String.format("<center><h2>%s %s - on %s - %s </h2></center>",
				suiteName,
				title,
				browser,
				getDateAsString()
		));
	
	}
	

	/*
	 * Method to get Date as String
	 */
	private String getDateAsString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	/** Starts HTML stream */
	protected void startHtml(PrintWriter out) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<!DOCTYPE html PUBLIC \n");//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">\n");
		//sb.append("<html xmlns="http:/"//www.w3.org/1999/xhtml">\n");
		sb.append("<head>\n");
		sb.append("<title>TestNG Report</title>\n");
		sb.append("<style type=text/css>\n");
		sb.append("table {margin-bottom:10px;border-collapse:collapse;empty-cells:show}\n");
		sb.append("td,th {border:1px solid #009;padding:.25em .5em}\n");
		sb.append(".result th {vertical-align:bottom}\n");
		sb.append(".param th {padding-left:1em;padding-right:1em}\n");
		sb.append(".param td {padding-left:.5em;padding-right:2em}\n");
		sb.append(".stripe td,.stripe th {background-color: #E6EBF9}\n");
		sb.append(".numi,.numi_attn {text-align:right}\n");
		sb.append(".total td {font-weight:bold}\n");
		sb.append(".passedodd td {background-color: lightgreen}\n");
		sb.append(".passedeven td {background-color: lightgreen}\n");
		sb.append(".skippedodd td {background-color: #FFFFBB}\n");
		sb.append(".skippedeven td {background-color: #FFFFBB}\n");
		sb.append(".failedodd td,.numi_attn {background-color: #FFBBBB}\n");
		sb.append(".failedeven td,.stripe .numi_attn {background-color: #D00}\n");
		sb.append(".stacktrace {white-space:pre;font-family:monospace}\n");
		sb.append(".totop {font-size:85%;text-align:center;border-bottom:2px solid #000}\n");
		sb.append(".summaryrow { text-align:left;\n\tpadding-right:2em }\n");
		sb.append("td.numi.").append(CLASS_UNEXPECTED).append(" { background-color:").append(COLOR_ERROR).append("; }");
		sb.append("td.numi.").append(CLASS_UNEXPECTED).append('.').append(CLASS_SKIPPED).append(" { background-color:").append(COLOR_WARN).append("; }");
		sb.append("</style>\n");
		sb.append("</head>\n");
		sb.append("<body>\n");
		out.println(sb.toString());
		
	}

	/** Finishes HTML stream */
	protected void endHtml(PrintWriter out) {
		out.println("<center> TestNG Report </center>");
		out.println("</body></html>");
	}

	// ~ Inner Classes --------------------------------------------------------
	/** Arranges methods by classname and method name */
	private class TestSorter implements Comparator<IInvokedMethod> {
		// ~ Methods
		// -------------------------------------------------------------

		/** Arranges methods by classname and method name */
		@Override
		public int compare(IInvokedMethod obj1, IInvokedMethod obj2) {
			int r = obj1.getTestMethod().getTestClass().getName().compareTo(obj2.getTestMethod().getTestClass().getName());
			return r;
		}
	}
	
	private class TestMethodSorter implements Comparator<ITestNGMethod> {
		@Override
		public int compare(ITestNGMethod obj1, ITestNGMethod obj2) {
			int r = obj1.getTestClass().getName().compareTo(obj2.getTestClass().getName());
			if (r == 0) {
				r = obj1.getMethodName().compareTo(obj2.getMethodName());
			}
			return r;
		}
	}

	private class TestResultsSorter implements Comparator<ITestResult> {
		@Override
		public int compare(ITestResult obj1, ITestResult obj2) {
			int result = obj1.getTestClass().getName().compareTo(obj2.getTestClass().getName());
			if (result == 0) {
				result = obj1.getMethod().getMethodName().compareTo(obj2.getMethod().getMethodName());
			}
			return result;
		}
	}

	@Override
	public void onStart(ISuite suite) {
		SuiteRunProperties props = new SuiteRunProperties(suite, null);
		try {
			System.out.println("*** Test Suite " + suite.getName() + " started ***");
			props.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		suite.setAttribute(TESTRUN_PROP, props);
	}

	@Override
	public void onFinish(ISuite suite) {
		SuiteRunProperties props = (SuiteRunProperties) suite.getAttribute(TESTRUN_PROP);
		if (props != null) {
//			ExecutionLog.log("UpdateTestRailResults:  " + props.getProperty("env.UpdateTestRailResults"));
//			ExecutionLog.log("TestrailProjectName:  " + props.getProperty("env.TestrailProjectName"));
//			ExecutionLog.log("TestrailRunName:  " + props.getProperty("env.TestrailRunName"));

			ExecutionLog.log("TestrailProjectName:  " + propertyReader.readProperty("TestrailProjectName"));
			ExecutionLog.log("TestrailRunName:  " + propertyReader.readProperty("TestrailRunName"));
			System.out.println(("*** Test Suite " + suite.getName() + " ending ***"));
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


}