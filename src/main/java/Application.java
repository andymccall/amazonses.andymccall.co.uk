import java.io.IOException;
import com.amazonaws.services.simpleemail.*;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.regions.*;

public class Application {

    static final String FROM = "mailme@andymccall.co.uk";
    static final String TO = "mailme@andymccall.co.uk";
    static final String SUBJECT = "Amazon SES test (AWS SDK for Java)";
    static final String HTMLBODY = "<h1>Amazon SES test (AWS SDK for Java)</h1>"
            + "<p>This email was sent with <a href='https://aws.amazon.com/ses/'>"
            + "Amazon SES</a> using the <a href='https://aws.amazon.com/sdk-for-java/'>"
            + "AWS SDK for Java</a>";

    static final String TEXTBODY = "This email was sent through Amazon SES "
            + "using the AWS SDK for Java.";

    public static void main(String[] args) {

        System.out.println("Amazon SES");
        System.out.println("==========");
        System.out.println();
        System.out.println("Sending email...");

        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            .withRegion(Regions.EU_WEST_1).build();

            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(TO))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(HTMLBODY))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(TEXTBODY)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(SUBJECT)))
                    .withSource(FROM);

            client.sendEmail(request);
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
    }

}
