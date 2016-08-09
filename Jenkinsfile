node {
    stage 'Checkout'
    git url: 'https://github.com/reasonthearchitect/AD_WatchStore.git'

    stage 'Build'
    sh "./gradlew clean build sonarqube"
    //step([$class: 'JUnitResultArchiver', testResults: '**/build/test-results/TEST-*.xml'])

    stage 'BuildRunDocker'
    sh 'docker kill watchstore'
    sh 'docker rm watchstore'
    sh 'docker build -t reasonthearchitect/watchstore .'
    sh 'docker run -d --name watchstore -p 8210:8210 reasonthearchitect/watchstore'
}