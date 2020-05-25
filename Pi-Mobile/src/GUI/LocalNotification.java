package GUI;

public class LocalNotification {
    
    /**
     * Constant used in {@link #setRepeatType(int) } to indicate that this
     * notification should not be repeated.
     */
    public static final int REPEAT_NONE=0;
    
    /**
     * Constant used in {@link #setRepeatType(int) } to indicate that this
     * notification should be repeated every 1 minute.
     */
    public static final int REPEAT_MINUTE=1;
    
    /**
     * Constant used in {@link #setRepeatType(int) } to indicate that this
     * notification should be repeated every hour.
     */
    public static final int REPEAT_HOUR=3;
    
    /**
     * Constant used in {@link #setRepeatType(int) } to indicate that this
     * notification should be repeated every day.
     */
    public static final int REPEAT_DAY=4;
    
    /**
     * Constant used in {@link #setRepeatType(int) } to indicate that this
     * notification should be repeated every week.
     */
    public static final int REPEAT_WEEK=5;
    
    // We don't support month or year right now because it is too complicated
    // to keep track of leap years, and days in month on platforms that only 
    // support repeat by milliseconds etc..
    
    private String id = "";
    private int badgeNumber=-1;
    private String alertBody = "";
    private String alertTitle = "";
    private String alertSound = "";
    private String alertImage = "";
    
    /**
     * Creates a new local notification
     */
    public LocalNotification() {
    }
    
    /**
     * Gets the badge number to set for this notification.
     * @return the badgeNumber
     */
    public int getBadgeNumber() {
        return badgeNumber;
    }

    /**
     * Gets the badge number to set for this notification.
     * @param badgeNumber the badgeNumber to set
     */
    public void setBadgeNumber(int badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    /**
     * Gets the alert body to be displayed for this notification.
     * @return the alertBody
     */
    public String getAlertBody() {
        return alertBody;
    }

    /**
     * Sets the alert body to be displayed for this notification.
     * @param alertBody the alertBody to set
     */
    public void setAlertBody(String alertBody) {
        this.alertBody = alertBody;
    }

    /**
     * Gets the alert title to be displayed for this notification.
     * @return the alertTitle
     */
    public String getAlertTitle() {
        return alertTitle;
    }

    /**
     * Sets the alert title to be displayed for this notification.
     * @param alertTitle the alertTitle to set
     */
    public void setAlertTitle(String alertTitle) {
        this.alertTitle = alertTitle;
    }

    /**
     * Gets the alert sound to be sounded when the notification arrives.  This 
     * should refer to a sound file that is bundled in the default package of your
     * app.
     * @return the alertSound
     */
    public String getAlertSound() {
        return alertSound;
    }

    /**
     * Sets the alert sound to be sounded when the notification arrives.  This 
     * should refer to a sound file that is bundled in the default package of your
     * app.
     * The name of the file must start with the "notification_sound" prefix.
     * 
     * <code><pre>
     * LocalNotification n = new LocalNotification();
     * n.setAlertSound("/notification_sound_bells.mp3");
     * </pre></code>
     *
     * @param alertSound the alertSound to set
     */
    public void setAlertSound(String alertSound) {
        this.alertSound = alertSound;
    }

    /**
     * Gets the ID of the notification.  The ID is the only information that is
     * passed to {@link LocalNotificationCallback#localNotificationReceived(java.lang.String) }
     * so you can use it as a lookup key to retrieve the rest of the information as required
     * from storage or some other mechanism.
     * 
     * The ID can also be used to cancel the notification later using {@link com.codename1.ui.Display#cancelLocalNotification(java.lang.String) }
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the notification.  The ID is the only information that is
     * passed to {@link LocalNotificationCallback#localNotificationReceived(java.lang.String) }
     * so you can use it as a lookup key to retrieve the rest of the information as required
     * from storage or some other mechanism.
     * 
     * The ID can also be used to cancel the notification later using {@link com.codename1.ui.Display#cancelLocalNotification(java.lang.String) }
     * 
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the notification image
     * @return image path
     */ 
    public String getAlertImage() {
        return alertImage;
    }

    /**
     * Sets an image to be displayed on the platform notifications bar, if the underlying platform
     * supports image displaying otherwise the image will be ignored.
     * @param image a path to the image, the image needs to be placed in the app root.
     */ 
    public void setAlertImage(String image) {
        this.alertImage = image;
    }
    
}