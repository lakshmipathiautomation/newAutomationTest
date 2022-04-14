pipeline
{
	agent any
	stages
	{
		stage('Build')
		{
			steps
			{
				echo "Build is Started"
				sh "mvn clean package -PRegression -DskipTests=true"
				echo "Build is Successful"
			}
		}
		stage('Smoke TestSuite')
		{
			parallel
			{
				stage('Chrome')
				{
					steps
					{
						echo "Smoke Test Execution is Started in Chrome"
						sh "mvn test -PSmoke -DBrowser=Chrome"
						echo "Smoke Test Execution is Successful in Chrome"
					}
				}
				stage('Firefox')
				{
					steps
					{
						echo "Smoke Test Execution is Started in Firefox"
						sh "mvn test -PSmoke -DskipTests=true"
						echo "Smoke Test Execution is Successful in Firefox"
					}
				}
			}
		}
		stage('Regression TestSuite')
		{
				stage('Chrome')
				{
					steps
					{
						echo "Regression Test Execution is Started in Chrome"
						sh "mvn test -PRegression -DBrowser=Chrome"
						echo "Regression Test Execution is Successful in Chrome"
					}
				}
				stage('Firefox')
				{
					steps
					{
						echo "Regression Test Execution is Started in Firefox"
						sh "mvn test -PRegression -DskipTests=true"
						echo "Regression Test Execution is Successful in Firefox"
					}
				}
			}
		}
		stage('Functional TestSuite')
		{
				stage('Chrome')
				{
					steps
					{
						echo "Functional Test Execution is Started in Chrome"
						sh "mvn test -PFunctional -DBrowser=Chrome"
						echo "Functional Test Execution is Successful in Chrome"
					}
				}
				stage('Firefox')
				{
					steps
					{
						echo "Functional Test Execution is Started in Firefox"
						sh "mvn test -PFunctional -DskipTests=true"
						echo "Functional Test Execution is Successful in Firefox"
					}
				}
			}
		}
		stage('Publish Reports')
		{
				stage('Extent Report')
				{
					steps
					{
						publishHTML([
						allowMissing: false, 
            					alwaysLinkToLastBuild: true, 
            					keepAll: false, 
						reportDir: 'D:\\Automation_Workspace\\Arexdata\\EmailableResults\\', 
            					reportFiles: '\test-output\emailable-report.html', 
            					reportName: 'Extent HTML Report', 
            					reportTitles: ''])
					}
				}
				}
			}
		}
		
				stage('Gmail')
				{
					steps
					{
						emailext body: "*${currentBuild.currentResult}:* Job Name: ${env.JOB_NAME} || Build Number: ${env.BUILD_NUMBER}\n More information at: ${env.BUILD_URL}",
						subject: 'Test Automation Pipeline Build Status',
						to: 'lakshmipathimunna@gmail.com'
					}
				}
			}
		}
	}
	post
	{
		failure 
		{
            		echo 'This Job is Failed - Notifications have been sent to Gmail..!!'
			color: 'good',
			message: "*${currentBuild.currentResult}:* Job Name: ${env.JOB_NAME} || Build Number: ${env.BUILD_NUMBER}\n More information at: ${env.BUILD_URL}"
        		
			emailext body: "*${currentBuild.currentResult}:* Job Name: ${env.JOB_NAME} || Build Number: ${env.BUILD_NUMBER}\n More information at: ${env.BUILD_URL}",
			subject: 'Test Automation Pipeline Build Status',
			to: 'lakshmipathimunna@gmail.com'
		}
        	unstable 
		{
           		echo 'This Job is Unstable - Notifications have been sent to Gmail..!!'
			message: "*${currentBuild.currentResult}:* Job Name: ${env.JOB_NAME} || Build Number: ${env.BUILD_NUMBER}\n More information at: ${env.BUILD_URL}"
			emailext body: "*${currentBuild.currentResult}:* Job Name: ${env.JOB_NAME} || Build Number: ${env.BUILD_NUMBER}\n More information at: ${env.BUILD_URL}",
			subject: 'Test Automation Pipeline Build Status',
			to: 'lakshmipathimunna@gmail.com'
		}
	}
}
