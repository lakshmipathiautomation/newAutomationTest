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
				sh "mvn clean"
				echo "Build is Successful"
			}
		}
		stage('Smoke TestSuite')
		{
			steps{
				echo "SmokeTest Started"
				sh "clean install"
				echo "Smoke Test Completed Successfully"
			}
		}
	}
}
