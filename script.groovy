def buildJar() {
    echo "building the application..."
    sh 'ls /var/jenkins_home/workspace/dockerImage/target/'
    sh 'cd /var/jenkins_home/workspace/dockerImage/target/'
    sh 'pwd'
    sh 'jar -xvf /var/jenkins_home/workspace/dockerImage/target/java-maven-app-1.1.0-SNAPSHOT.jar'
    sh 'ls /var/jenkins_home/workspace/dockerImage/target/'
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t aishafathy/demo-app:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push aishafathy/demo-app:jma-2.0'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
