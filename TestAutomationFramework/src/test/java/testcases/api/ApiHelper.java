package testcases.api;

import com.testframework.FormatHelper;
import com.testframework.Utils;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.CommentResponse;
import com.testframework.models.Comment;
import com.testframework.models.User;
import com.testframework.models.interfaces.Dated;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * The ApiHelper class provides utility methods for API testing and validation.
 */
public class ApiHelper {

    /**
     * Asserts the equivalence between a given comment and its corresponding comment response.
     *
     * @param comment         the comment object to be compared
     * @param commentResponse the comment response object to be compared
     */
    public static void assertComment(Comment comment, CommentResponse commentResponse) {
        Assertions.assertAll(
                () -> Assertions.assertEquals(comment.getCommentId(), commentResponse.getCommentId(),
                        "The id doesn't match."),
                () -> Assertions.assertEquals(comment.getContent(), commentResponse.getContent(),
                        "The content doesn't match."),
                () -> {
                    int differenceThreshold = Integer.parseInt(Utils.getConfigPropertyByKey("weare.timeDifferenceThreshold"));
                    LocalDateTime actualDateTime = FormatHelper.parseDateTimeFromString(commentResponse.getDate());

                    assertDateTimeApproxEqual(comment.getCreationDateTime(), actualDateTime, differenceThreshold);
                }
        );
    }

    /**
     * Asserts the equivalence between two comment response objects.
     *
     * @param response1 the first comment response object for comparison
     * @param response2 the second comment response object for comparison
     */
    public static void assertComment(CommentResponse response1, CommentResponse response2) {
        Assertions.assertAll(
                () -> Assertions.assertEquals(response1.getCommentId(), response2.getCommentId(),
                        "The id doesn't match."),
                () -> Assertions.assertEquals(response1.getContent(), response2.getContent(),
                        "The content doesn't match."),
                () -> {
                    int differenceThreshold = Integer.parseInt(Utils.getConfigPropertyByKey("weare.timeDifferenceThreshold"));
                    LocalDateTime dateTime1 = FormatHelper.parseDateTimeFromString(response1.getDate());
                    LocalDateTime dateTime2 = FormatHelper.parseDateTimeFromString(response2.getDate());

                    assertDateTimeApproxEqual(dateTime2, dateTime1, differenceThreshold);
                }
        );
    }

    /**
     * Asserts the approximate equality of two given LocalDateTime instances within a specified threshold in seconds.
     *
     * @param dt1              the first LocalDateTime object for comparison
     * @param dt2              the second LocalDateTime object for comparison
     * @param thresholdSeconds the threshold in seconds used for comparison
     */
    public static void assertDateTimeApproxEqual(LocalDateTime dt1, LocalDateTime dt2, long thresholdSeconds) {
        Assertions.assertTrue(Dated.dateTimeApproximatelyEquals(dt1, dt2, thresholdSeconds),
                "Date times differ by more than " + thresholdSeconds + " seconds.");
    }

}
