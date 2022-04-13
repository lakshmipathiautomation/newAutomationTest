package locators;

public class Admin_AlertsLocators {

    public static final String createAlertBtn = "//button[text()='Create']";
    public static final String selectType = "//label[text()='Type']/..//span";
    public static final String integrationType = "//label[text()='Integration']/..//span";
    public static final String alertName = "//label[text()='Name']/..//input";
    public static final String severity = "//label[text()='Severity']/..//span";
    public static final String emailCheckbox = "//input[@id='Email']";
    public static final String sendEmailTo = "//label[text()='Send Email to']/../following-sibling::div/input";
    public static final String saveButton = "//button[text()='Save']";
    public static final String fileFolder = "//label[text()='File/Folder']/..//input";
    public static final String operation = "//label[text()='Operation']/..//span";
    public static final String runScriptInAgent = "//label[text()='Run Script in agent']/..//input";
    public static final String editAlert = "//td[text()='@override']/following-sibling::td/button[text()='Edit']";
    public static final String deleteAlert = "//td[text()='@override']/following-sibling::td/button[text()='Delete']";
    public static final String deleteOKButton = "//button[text()='OK']";
    public static final String headerForSort = "//*[@class='k-column-title' and text()='@override']";
    public static final String filterInTooltip = "//*[@class='k-tooltip-content']//tr/td";
    public static final String noReocrdFound = "//*[@class='k-grid-norecords']/td[text()='No records available.']";
}
