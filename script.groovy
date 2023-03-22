def buildJar() {
    echo "building the application..."
//     sh 'find . -name POM.xml'
//     sh 'ls /usr/share/jenkins'
//     sh 'ls /usr/share/xml'
//     sh "mvn package"
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerHunRepo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t aishafathy/demo-app:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push aishafathy/demo-app:jma-2.0'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
