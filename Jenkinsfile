pipeline {
  agent any
  tools {
    maven 'Maven'
  }
  stages {
    stage("build jar") {
      steps {
        script {
          echo "building the docker image"
          sh 'mvn package'
        }
      }
    }
    stage("build image") {
      steps {
        script {
          echo "building the docker image"
          withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
            sh 'docker build -t aishafathy/my-repo-123:jenko .'
            sh "echo $PASS | docker login --username $USER --password-stdin"
            sh 'docker push aishafathy/my-repo-123:jenko'
          }
        }
      }
    }
  }
