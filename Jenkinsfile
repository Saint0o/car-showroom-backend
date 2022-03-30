node {
    checkout scm
    def image;

    stage ('Build Docker Image') {
        image = docker.build("car-showroom-backend:${env.BUILD_ID}")
    }

    stage ('Push Docker Image in Nexus') {
        docker.withRegistry('http://nexus:8123/repository/docker-images/', 'Nexus') {
           image.push()
        }
    }
}
