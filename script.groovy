def buildJar() {
    echo "building the application..."
    sh 'git credential-osxkeychain'
    sh 'curl -O http://github-media-downloads.s3.amazonaws.com/osx/git-credential-osxkeychain'
    sh 'chmod u+x /usr/local/bin/git-credential-osxkeychain'
    sh 'git config --global credential.helper osxkeychain'
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
