pipeline{
    agent any
    stages{
        stage('Get Code'){
            steps{
                deleteDir()
                checkout scmGit(branches: [[name: '**']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/dgaznares/prueba-inditex.git']])
                sh 'ls -la'
                echo WORKSPACE
            }
        }
        stage('Build'){
            steps{
                sh '''
                    mvn clean package -DskipTests
                '''
            }
        }
        stage('Integration-Test y cobertura (JaCoCo)') {
            steps {
                sh '''
                     mvn verify
                     find . -name jacoco.exec
                '''
                publishHTML(target: [
                        reportDir: './ms-prices/target/site/jacoco',
                        reportFiles: 'index.html',
                        reportName: 'JaCoCo Coverage Report'])
            }
        }
        stage('Performance'){
             steps{
                 sh '''
                    echo "Levanamos aplicación"
                    cd ./ms-prices
                    mvn spring-boot:run &
                    timeout=30
                    while ! nc -z 127.0.0.1 8003; do
                       sleep 1
                       timeout=$((timeout - 1))
                       if [ $timeout -le 0 ]; then
                          echo "Timeout esperando a MS-PRICES"
                          PRUEBA_INDITEX_PID = $(ps | grep 'spring-boot:run' | grep -v grep | awk '{print $1}')
                          echo $PRUEBA_INDITEX_PID
                          kill $PRUEBA_INDITEX_PID
                          exit 1
                       fi
                    done
                    echo 'Lanzamos las pruebas de Rendimiento...'
                    jmeter -n -t ./jmeter/ms-prices-test-plan.jmx -f -l ms-prices-test-plan-result.jtl 
                 '''
                perfReport sourceDataFiles: './ms-prices/ms-prices-test-plan-result.jtl'
             }
         }

    }
}