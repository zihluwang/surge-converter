# Surge Converter

This is a subscription converter for Surge 4/5.

## How to install

### 1. Create a config

1. Create a folder to store the configuration, assuming it is `/path/to/config`.
2. Add a configuration file to the configuration folder: `application.yml` or `application.properties`, we will use the `yaml` file as an example.
   ```yaml
   app:
     # This specifies the host that Surge automatically downloads the configuration file. For better
     # experience, it is highly recommended to use a domain name.
     host: http://127.0.0.1
     # This specifies which `subconverter` you want to use. HOST ONLY.
     converter-host: subconverter.example.org
     # This specifies which scheme `subconverter` is being used. SCHEME ONLY.
     converter-scheme: https
   
   # This app is developed by Spring Boot, you can also add other spring boot configurations to customise this application.
   ```

#### For those who use Docker

1. Run `docker run -itd --name <container_name> -p <listen_port>:8080 -e TZ='<timezone>' -v </path/to/config>:/app/config zihluwang/surge-converter:latest`.

#### For those who don't use Docker

1. Install any Java 21 distribution, such as **Microsoft Open JDK**, **Amazon Corretto**, **Oracle JDK**, etc.
2. Grant execution authorisation to `gradlew` or `gradlew.bat`, depending on your operating system.
3. Run `gradlew build` to build a executable jar package, you may append `-x test` to skip test task.
4. Let's assume your current working directory is `/path/to/config`, and you can execute `java -jar /path/to/executable.jar`.

For example, the jar file is located at `/home/user/surge-converter/surge-converter-${APP_VERSION}.jar`, the config directory is located at `/home/user/surge-converter/config`, and your working directory is located at `/home/user/surge-converter`.

### How to use

Please refer to [API docs](/api.yaml) for more details.
