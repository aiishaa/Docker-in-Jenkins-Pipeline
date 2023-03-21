def buildJar() {
    echo "building the application..."
    // sh 'ls /var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/Maven'
    sh 'mvn org.apache.maven.plugins:maven-install-plugin:3.1.0:install-file -Dfile=/var/jenkins_home/workspace/dockerImage -DpomFile=/var/jenkins_home/workspace/dockerImage'
    sh 'mvn package'
    sh 'cd ..'
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
