# Mail Content Generator

### Build
```shell
mvn clean package
```
### Run
```shell
java -jar target/mail-generator-1.0.0.jar
```
### Execute
#### Send a POST request to http://localhost:8080/mg/api/v1/mail/generate with below payload
```json
{
  "mailTemplate": "Hi {user}, welcome to {company}",
  "parameters": {
    "user": "Bob",
    "company": "ABC Solutions"
  }
}
```
...and a formatted content will be responded
```json
{
  "mailContent": "Hi Bob, welcome to ABC Solutions"
}
```

### Terminate
```shell
Ctrl + C
```