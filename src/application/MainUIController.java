package application;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public class MainUIController implements EventHandler<ActionEvent> {

    private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private static int numberOfClicks = 0;

    private HostServices hostServices;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @FXML
    private BorderPane rootPane;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab menuTab;

    @FXML
    private Button rulesBtn;

    @FXML
    private Button supportTicketFormBtn;

    @FXML
    private Button interviewProcedureBtn;

    @FXML
    private Button interviewQuestionsBtn;

    @FXML
    private Button startBtn;

    @FXML
    private Hyperlink developerLabel;

    @FXML
    private Label versionLabel;

    @FXML
    private Hyperlink bugLabel;

    @FXML
    private Button supportCaseFormBtn;

    @FXML
    private Tab linksTab;

    @FXML
    private TextField accountLinkTxt;

    @FXML
    private TextField applicationLinkTxt;

    @FXML
    private ComboBox<String> prevFailedInvCmb;

    @FXML
    private TextField linksToFailedTxt;

    @FXML
    private Button linksNextPageBtn;

    @FXML
    private Tab generalTab;

    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField rpNameTxt;

    @FXML
    private TextField locationTxt;

    @FXML
    private ComboBox<String> languageCmb;

    @FXML
    private Spinner<Integer> ageSpinner;

    @FXML
    private Button ageCheckBtn;

    @FXML
    private DatePicker dobDatePicker;

    @FXML
    private TextField foundAboutTxt;

    @FXML
    private Button generalPrevPageBtn;

    @FXML
    private Button generalNextPageBtn;

    @FXML
    private Tab interviewTab;

    @FXML
    private Tab supportTicketTab;

    @FXML
    private Tab supportCaseTab;

    @FXML
    private TextField halfIncorrectTxt;

    @FXML
    private TextField incorrectTxt;

    @FXML
    private ComboBox<String> ruleTestCmb;

    @FXML
    private TextField notesTxt;

    @FXML
    private Button interviewPrevPageBtn;

    @FXML
    private Button finishBtn;

    @FXML
    private Tab logTab;

    @FXML
    private Button generateBtn;

    @FXML
    private Button copyBtn;

    @FXML
    private Button postToForumsBtn;

    @FXML
    private Button postToSecLogBtn;

    @FXML
    private TextArea logTextArea;

    @FXML
    private TextField nameOfMemberForumsTxt;

    @FXML
    private DatePicker dateOfTicket;

    @FXML
    private TextField userProfileLinkTxt;

    @FXML
    private TextField supportTicketLink;

    @FXML
    private TextField issueDescTxt;

    @FXML
    private ComboBox<String> issueResolvedCmb;

    @FXML
    private Button postForumsLogBtn;

    @FXML
    private TextField memberNameTxt;

    @FXML
    private CheckBox serverIssueChk;

    @FXML
    private CheckBox tsIssueChk;

    @FXML
    private CheckBox modPkgChk;

    @FXML
    private CheckBox genQuestionChk;

    @FXML
    private ComboBox<String> issueResolvedTSCmb;

    @FXML
    private Button postTSLogBtn;

    @FXML
    private ImageView logoImg;

    @FXML
    private Label messageLbl;

    @FXML
    private Button themeBtn;

    @FXML
    void initialize() {
	Tooltip tooltip = new Tooltip("Renegade!!!");
	Utility.hackTooltipStartTiming(tooltip);
	Tooltip.install(logoImg, tooltip);

	this.versionLabel.setText("Version " + Main.APP_VERSION);

	SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
	this.ageSpinner.setValueFactory(valueFactory);

	this.dobDatePicker.setValue(LocalDate.now());

	this.prevFailedInvCmb.getItems().addAll("True", "False");
	this.prevFailedInvCmb.getSelectionModel().select("");

	this.languageCmb.getItems().addAll("True", "False");
	this.languageCmb.getSelectionModel().select("");

	this.ruleTestCmb.getItems().addAll("Passed", "Failed");
	this.ruleTestCmb.getSelectionModel().select("");

	this.issueResolvedCmb.getItems().addAll("Yes", "No");
	this.issueResolvedCmb.getSelectionModel().select("");

	this.issueResolvedTSCmb.getItems().addAll("Yes", "No");
	this.issueResolvedTSCmb.getSelectionModel().select("");

	this.interviewProcedureBtn.setOnAction(this);
	this.interviewQuestionsBtn.setOnAction(this);
	this.rulesBtn.setOnAction(this);
	this.supportTicketFormBtn.setOnAction(this);
	this.supportCaseFormBtn.setOnAction(this);
	this.startBtn.setOnAction(this);
	this.developerLabel.setOnAction(this);
	this.bugLabel.setOnAction(this);
	this.linksNextPageBtn.setOnAction(this);
	this.generalPrevPageBtn.setOnAction(this);
	this.generalNextPageBtn.setOnAction(this);
	this.interviewPrevPageBtn.setOnAction(this);
	this.finishBtn.setOnAction(this);
	this.generateBtn.setOnAction(this);
	this.copyBtn.setOnAction(this);
	this.postToForumsBtn.setOnAction(this);
	this.postToSecLogBtn.setOnAction(this);
	this.ageCheckBtn.setOnAction(this);
	this.themeBtn.setOnAction(this);
	this.postForumsLogBtn.setOnAction(this);
	this.postTSLogBtn.setOnAction(this);

	this.prevFailedInvCmb.setOnAction(this);

	this.logoImg.setOnMouseClicked((event) -> {
	    this.messageLbl.setText("Number of clicks : " + ++numberOfClicks);
	    this.messageLbl.setVisible(true);

	    if (numberOfClicks % 10 == 0) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Main.APP_TITLE);
		alert.setContentText(Main.messages[new Random().nextInt(Main.messages.length)]);
		alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label)
			.forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
		alert.showAndWait();
	    }
	});

	loadTheme(null);
    }

    public HostServices getHostServices() {
	return hostServices;
    }

    public void setHostServices(HostServices hostServices) {
	this.hostServices = hostServices;
    }

    @Override
    public void handle(ActionEvent event) {
	if (event.getSource().equals(interviewProcedureBtn)) {
	    this.hostServices.showDocument(Main.properties.getProperty("interview_procedure_url"));
	} else if (event.getSource().equals(interviewQuestionsBtn)) {
	    this.hostServices.showDocument(Main.properties.getProperty("interview_questions_url"));
	} else if (event.getSource().equals(rulesBtn)) {
	    this.hostServices.showDocument(Main.properties.getProperty("official_rules_url"));
	} else if (event.getSource().equals(supportTicketFormBtn)) {
	    this.tabPane.getSelectionModel().select(5);
	} else if (event.getSource().equals(supportCaseFormBtn)) {
	    this.tabPane.getSelectionModel().select(6);
	} else if (event.getSource().equals(startBtn)) {
	    this.startTime = LocalDateTime.now();
	    this.toggleTabs(true);
	    this.generateBtn.setDisable(true);
	    this.copyBtn.setDisable(true);
	    this.postToForumsBtn.setDisable(true);
	    this.postToSecLogBtn.setDisable(true);
	    this.setControlDefaults();
	    // this.startBtn.setDisable(true);
	    tabPane.getSelectionModel().selectNext();
	} else if (event.getSource().equals(generalPrevPageBtn) || event.getSource().equals(interviewPrevPageBtn)) {
	    tabPane.getSelectionModel().selectPrevious();
	} else if (event.getSource().equals(linksNextPageBtn)) {
	    Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Confirmation");
	    alert.setHeaderText(null);
	    alert.setContentText("Does the applicant have an ACCEPTED Civilian Application?");
	    alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

	    Optional<ButtonType> result = alert.showAndWait();

	    if (result.get().equals(ButtonType.YES)) {
		tabPane.getSelectionModel().selectNext();
	    }
	} else if (event.getSource().equals(generalNextPageBtn)) {
	    tabPane.getSelectionModel().selectNext();
	} else if (event.getSource().equals(finishBtn)) {
	    this.endTime = LocalDateTime.now();
	    this.toggleTabs(false);
	    this.generateBtn.setDisable(false);
	    tabPane.getSelectionModel().selectNext();
	} else if (event.getSource().equals(generateBtn)) {
	    this.logTextArea.setText(generateLog());
	    this.startBtn.setDisable(false);
	    this.generateBtn.setDisable(true);
	    this.copyBtn.setDisable(false);
	    this.postToForumsBtn.setDisable(false);
	    this.postToSecLogBtn.setDisable(false);
	} else if (event.getSource().equals(copyBtn)) {
	    StringSelection selection = new StringSelection(logTextArea.getText());
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(selection, selection);
	    this.copyBtn.setDisable(true);
	} else if (event.getSource().equals(postToForumsBtn)) {
	    this.hostServices.showDocument(Main.properties.getProperty("post_to_forums_url"));
	    this.postToForumsBtn.setDisable(true);
	} else if (event.getSource().equals(postToSecLogBtn)) {
	    this.postToSecLog();
	} else if (event.getSource().equals(ageCheckBtn)) {
	    int minAge = Integer.parseInt(Main.properties.getProperty("min_age"));

	    if (ageSpinner.getValue() < minAge) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Arma 3 Life Support Client");
		alert.setContentText("User is under the age of " + minAge);
		alert.showAndWait();
	    } else if (!ageSpinner.getValue().equals(Utility.getAgeFromDate(dobDatePicker.getValue()))) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Arma 3 Life Support Client");
		alert.setContentText("The age and the date of birth do not match.");
		alert.showAndWait();
	    } else {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Arma 3 Life Support Client");
		alert.setContentText("User is of appropriate age");
		alert.showAndWait();
	    }
	} else if (event.getSource().equals(postForumsLogBtn)) {
	    postSupportTicketForum();
	} else if (event.getSource().equals(postTSLogBtn)) {
	    postSupportCaseForm();
	} else if (event.getSource().equals(themeBtn)) {
	    String oldTheme = Main.properties.getProperty("theme");
	    String theme = oldTheme;

	    switch (oldTheme) {
	    case "theme-1":
		theme = "theme-2";
		break;
	    case "theme-2":
		theme = "theme-3";
		break;
	    case "theme-3":
		theme = "theme-4";
		break;
	    case "theme-4":
		theme = "theme-1";
		break;
	    }

	    Main.properties.setProperty("theme", theme);
	    Main.saveProperties("Changed theme to " + theme + " on " + new Date().toString());

	    loadTheme(oldTheme);
	}

	if (event.getSource().equals(prevFailedInvCmb)) {
	    boolean isDisabled = prevFailedInvCmb.getValue().equals("False") || prevFailedInvCmb.getValue().equals("");
	    linksToFailedTxt.setDisable(isDisabled);
	}

	if (event.getSource().equals(developerLabel)) {
	    this.hostServices.showDocument(Main.properties.getProperty("developer_url"));
	}

	if (event.getSource().equals(bugLabel)) {
	    this.hostServices.showDocument(Main.properties.getProperty("report_bug_url"));
	}
    }

    /**
     * Generate the log.
     *
     * @return
     */
    private String generateLog() {
	StringBuilder builder = new StringBuilder();

	builder.append("[b]Forum Username: [/b]").append(usernameTxt.getText());
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Roleplay Name: [/b]").append(rpNameTxt.getText());
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Location: [/b]").append(locationTxt.getText());
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Speaks English Fluently: [/b]").append(languageCmb.getSelectionModel().getSelectedItem());
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Age: [/b]").append(ageSpinner.getValue());
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Date Of Birth: [/b]").append(dobDatePicker.getValue());
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Ages Matching: [/b]")
		.append(ageSpinner.getValue().equals(Utility.getAgeFromDate(dobDatePicker.getValue())));
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Found out about Arma 3 Life : [/b]").append(foundAboutTxt.getText());
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Account Link: [/b]").append(accountLinkTxt.getText());
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Civilian Interview Link: [/b]").append(applicationLinkTxt.getText());
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Previous Failed Interview Link: [/b]")
		.append(prevFailedInvCmb.getValue().equals("True") ? linksToFailedTxt.getText() : "");
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Start Time: [/b]").append(this.startTime.format(DT_FORMATTER));
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]End Time: [/b]").append(this.endTime.format(DT_FORMATTER));
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Interview Status: [color=").append(ruleTestCmb.getValue().equals("Passed") ? "green" : "red")
		.append("]").append(ruleTestCmb.getValue()).append("[/color][/b]");
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Half Incorrect Answers: [/b]").append(halfIncorrectTxt.getText());
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Incorrect Answers: [/b]").append(incorrectTxt.getText());
	builder.append(System.getProperty("line.separator"));

	builder.append("[b]Notes: [/b]").append(notesTxt.getText());
	builder.append(System.getProperty("line.separator"));

	builder.append(System.getProperty("line.separator"));
	builder.append("Created with Arma 3 Life Support Client V" + Main.APP_VERSION);

	return builder.toString();
    }

    /**
     * Post to secondary Log.
     */
    private void postToSecLog() {
	StringBuilder baseUrl = new StringBuilder(Main.properties.getProperty("post_to_secondary_log_url"));

	String name;
	String forumLogLink;
	String interviewPassed;
	String failType;
	String incorrectAnswers = "Incorrect: " + incorrectTxt.getText() + " | Half Incorrect: "
		+ halfIncorrectTxt.getText();

	TextInputDialog dialog = new TextInputDialog();
	dialog.setTitle(Main.APP_TITLE);
	dialog.setHeaderText("What is your name?");

	Optional<String> result = dialog.showAndWait();

	if (result.isPresent()) {
	    name = result.get();

	    dialog = new TextInputDialog();
	    dialog.setTitle(Main.APP_TITLE);
	    dialog.setHeaderText("Link to their forum log.");

	    result = dialog.showAndWait();

	    if (result.isPresent()) {
		forumLogLink = result.get();

		List<String> choices = new ArrayList<>();
		choices.add("1 Week - 3 Incorrect Answers");
		choices.add("3 Month - Suspected Cheating");
		choices.add("Permanent - Caught Cheating");
		choices.add("Underage");

		ChoiceDialog<String> choicesDialog = new ChoiceDialog<>("1 Week - 3 Incorrect Answers", choices);
		choicesDialog.setTitle(Main.APP_TITLE);
		choicesDialog.setHeaderText("Fail Type");

		if (ruleTestCmb.getSelectionModel().getSelectedItem().equals("Failed")) {
		    result = choicesDialog.showAndWait();
		    interviewPassed = "No";
		} else {
		    result = Optional.of("N/A");
		    interviewPassed = "Yes";
		}

		if (result.isPresent()) {
		    failType = result.get();

		    try {
			baseUrl.append("&entry.1552706886=").append(URLEncoder.encode(name, "UTF-8"));
			baseUrl.append("&entry.1524315324=").append(URLEncoder.encode(rpNameTxt.getText(), "UTF-8"));
			baseUrl.append("&entry.1320800886=").append(URLEncoder.encode(forumLogLink, "UTF-8"));
			baseUrl.append("&entry.1149439022=").append(URLEncoder.encode(interviewPassed, "UTF-8"));
			baseUrl.append("&entry.518918352=").append(URLEncoder.encode(failType, "UTF-8"));
			baseUrl.append("&entry.126377261=").append(URLEncoder.encode(incorrectAnswers, "UTF-8"));
		    } catch (UnsupportedEncodingException e) {
			System.err.println(e.getLocalizedMessage());
		    }

		    this.hostServices.showDocument(baseUrl.toString());
		    this.postToSecLogBtn.setDisable(true);
		}
	    }
	}
    }

    /**
     * Toggle tabs according to function.
     *
     * @param enabled
     */
    private void toggleTabs(boolean enabled) {
	this.linksTab.setDisable(!enabled);
	this.generalTab.setDisable(!enabled);
	this.interviewTab.setDisable(!enabled);
    }

    /**
     * Set the default values.
     */
    private void setControlDefaults() {
	this.ageSpinner.getValueFactory().setValue(0);
	this.dobDatePicker.setValue(LocalDate.now());
	this.prevFailedInvCmb.getSelectionModel().select("");
	this.languageCmb.getSelectionModel().select("");
	this.ruleTestCmb.getSelectionModel().select("");
	this.logTextArea.clear();

	for (Tab tab : tabPane.getTabs()) {
	    for (Node node : ((Parent) tab.getContent()).getChildrenUnmodifiable()) {
		if (node instanceof TextField) {
		    ((TextField) node).clear();
		}
	    }
	}
    }

    /**
     * Load the theme from the property file and set it.
     *
     * @param theme
     */
    private void loadTheme(String oldTheme) {
	String theme = Main.properties.getProperty("theme");

	// If there is an old theme, remove it's class.
	if (oldTheme != null) {
	    this.rootPane.getStyleClass().remove(oldTheme);
	}

	this.rootPane.getStyleClass().add(theme);
    }

    /**
     * Post the support ticket form.
     */
    private void postSupportTicketForum() {
	StringBuilder baseUrl = new StringBuilder(Main.properties.getProperty("post_log_support_ticket"));

	String theirName = this.nameOfMemberForumsTxt.getText();
	LocalDate date = this.dateOfTicket.getValue();
	String linkToProfile = this.userProfileLinkTxt.getText();
	String linkToTicket = this.supportTicketLink.getText();
	String description = this.issueDescTxt.getText();
	String issueResolved = this.issueResolvedCmb.getValue();
	TextInputDialog dialog = new TextInputDialog();
	dialog.setTitle(Main.APP_TITLE);
	dialog.setHeaderText("What is your name?");

	Optional<String> result = dialog.showAndWait();

	if (result.isPresent()) {
	    String username = result.get();

	    try {
		baseUrl.append("&entry.1552706886=").append(URLEncoder.encode(username, "UTF-8"));
		baseUrl.append("&entry.1524315324=").append(URLEncoder.encode(theirName, "UTF-8"));
		baseUrl.append("&entry.1235194949=")
			.append(URLEncoder.encode(date != null ? date.toString() : "", "UTF-8"));
		baseUrl.append("&entry.518918352=").append(URLEncoder.encode(linkToProfile, "UTF-8"));
		baseUrl.append("&entry.1657146232=").append(URLEncoder.encode(linkToTicket, "UTF-8"));
		baseUrl.append("&entry.248710896=").append(URLEncoder.encode(description, "UTF-8"));
		baseUrl.append("&entry.1069050933=").append(URLEncoder.encode(issueResolved, "UTF-8"));
	    } catch (UnsupportedEncodingException e) {
		System.err.println(e.getLocalizedMessage());
	    }
	}

	this.hostServices.showDocument(baseUrl.toString());
    }

    /**
     * Post the support case form.
     */
    private void postSupportCaseForm() {
	StringBuilder baseUrl = new StringBuilder(Main.properties.getProperty("post_log_team_speak"));

	String thierName = this.memberNameTxt.getText();
	String serverIssue = this.serverIssueChk.isSelected() ? this.serverIssueChk.getText() : "";
	String tsIssue = this.tsIssueChk.isSelected() ? this.tsIssueChk.getText() : "";
	String modPakInstallation = this.modPkgChk.isSelected() ? this.modPkgChk.getText() : "";
	String general = this.genQuestionChk.isSelected() ? this.genQuestionChk.getText() : "";
	String issueResolved = this.issueResolvedTSCmb.getValue();

	TextInputDialog dialog = new TextInputDialog();
	dialog.setTitle(Main.APP_TITLE);
	dialog.setHeaderText("What is your name?");

	Optional<String> result = dialog.showAndWait();

	if (result.isPresent()) {
	    String username = result.get();

	    try {
		baseUrl.append("&entry.1552706886=").append(URLEncoder.encode(username, "UTF-8"));
		baseUrl.append("&entry.1524315324=").append(URLEncoder.encode(thierName, "UTF-8"));
		baseUrl.append("&entry.1149439022=").append(URLEncoder.encode(serverIssue, "UTF-8"));
		baseUrl.append("&entry.1149439022=").append(URLEncoder.encode(tsIssue, "UTF-8"));
		baseUrl.append("&entry.1149439022=").append(URLEncoder.encode(modPakInstallation, "UTF-8"));
		baseUrl.append("&entry.1149439022=").append(URLEncoder.encode(general, "UTF-8"));
		baseUrl.append("&entry.518918352=").append(URLEncoder.encode(issueResolved, "UTF-8"));
	    } catch (UnsupportedEncodingException e) {
		System.err.println(e.getLocalizedMessage());
	    }

	    this.hostServices.showDocument(baseUrl.toString());
	}
    }
}
