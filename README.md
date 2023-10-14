# Email Service with Spring Boot
This is a simple email service developed using Spring Boot. It enables users to send emails with or without attachments and is configured to work with the **Brevo** email service.


## Features
- Send plain text emails.
- Send emails with file attachments.

## Prerequisites

- Java Development Kit (JDK)
- Spring Boot
- Postman or any REST client for testing

## Installation

- Clone the repository:

   ```bash
   git clone https://github.com/RashTGr/spring-mailer.git


## Usage
### Automated Email Sending
On the initial launch, the email sending service is configured to operate independently 
of the API endpoints and begin processing emails immediately. Emails will be sent out 
at **10-minute** intervals.

### Sending a Simple Email
To send a simple text email, make a POST request to `/send` with the following JSON body:
```json
{
  "from": "sender@example.com",
  "to": "recipient@example.com",
  "subject": "Hello",
  "body": "This is a test email."
}
```

### Sending an Email with Attachment
To send an email with an attachment, make a POST request to `/send-att` with the following JSON body:
```json
{
  "from": "sender@example.com",
  "to": "recipient@example.com",
  "subject": "Attachment Test",
  "body": "Please check the attachment."
}
```
Please be aware that, for the sake of illustration, the attachment feature is implemented using a hardcoded manner.

## Testing
### Postman
**Postman** for exploring and testing the APIs. Creating requests to send emails with or without attachments is as easy as following the steps above.

### Swagger
When the application is up and running, you can also visit **Swagger UI** 
by hitting http://localhost:8080/swagger-ui/index.html#/ in the browser 
you are using. To facilitate testing of your endpoints, Swagger provides 
a straightforward interface.
