public class MainActivity{
	
	public static void main(String[]args) {
		ActivityMain();
	} //public static void main(String[]args)
	
	public static void ActivityMain() {
		Thread HomescreenGUI = new Thread(new Homescreen());
		HomescreenGUI.start();
	} //public static void ActivityMain()
	
	public static void ActivitySignUp() {
		Thread SignUpGUI = new Thread(new ApplicantSignup());
		SignUpGUI.start();
	} //public static void ActivitySignUp()
	
	public static void ActivityNewsFeed() {
		Thread NewsFeedGUI = new Thread(new NewsFeed());
		NewsFeedGUI.start();
	} //public static void ActivityNewsFeed()
	
	public static void ActivityNewsFeedSpecific() {
		Thread NewsFeedSGUI = new Thread(new NewsFeedSpecific());
		NewsFeedSGUI.start();
	} //public static void ActivityNewsFeedSpecific()
	
	public static void ActivityProfile() {
		Thread ProfileGUI = new Thread(new Profile());
		ProfileGUI.start();
	} //public static void ActivityProfile()
	
	public static void ActivityCreateOrg() {
		Thread CreateOrgGUI = new Thread(new CreateAnOrg());
		CreateOrgGUI.start();
	} //public static void ActivityCreateOrg()

	public static void ActivityEditOrg(String strOrgName) {
		Thread EditOrgGUI = new Thread(new EditOrg(strOrgName));
		EditOrgGUI.start();
	} //public static void ActivityEditOrg(String strOrgName)

	public static void ActivityLeaveOrg() {
		Thread LeaveOrgGUI = new Thread(new LeaveAnOrg());
		LeaveOrgGUI.start();
	} //public static void ActivityLeaveOrg()
	
	public static void ActivitySearch() {
		Thread SearchGUI = new Thread(new Search());
		SearchGUI.start();
	} //public static void ActivitySearch()
	
	public static void ActivityClickingAnOrg() {
		Thread ClickingAnOrgGUI = new Thread(new ClickingAnOrg());
		ClickingAnOrgGUI.start();
	} //public static void ActivityClickingAnOrg()
	
} //public class MainActivity