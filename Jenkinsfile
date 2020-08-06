pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        bat 'mvn -version'
        bat 'mvn clean compile'
      }
    }

    stage('Test') {
      steps {
        bat 'mvn test'
      }
    }

    stage('Deploy') {
      steps {
        echo 'Deploying..'
      }
    }

  }
}
