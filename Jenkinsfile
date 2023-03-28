pipeline {
    agent any
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }
        stage("push image") {
            steps {
                script {
                    gv.pushImage()
                }
            }
        }
    }
}
