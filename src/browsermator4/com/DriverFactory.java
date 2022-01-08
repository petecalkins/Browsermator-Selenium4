/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browsermator4.com;

import java.io.File;
import java.util.HashMap;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 *
 * @author Pete
 */
public class DriverFactory {
    WebDriver driver;
    String targetbrowser;
    String WEBDRIVERSDIR;
    String BrowsermatorAppFolder;
    BrowserMatorConfig appConfig = new BrowserMatorConfig();
   
String OSType;

String firefox_path;
String chrome_path;
String chrome_main_path;
 PageLoadStrategy PageLoadConstant= PageLoadStrategy.NORMAL;
 UnexpectedAlertBehaviour promptBehaviorConstant = UnexpectedAlertBehaviour.DISMISS;
 
 String stringPageLoadConstant = "normal";
 String downloadDir = "";
 String waitForLoad;
String promptBehavior;
SeleniumTestToolData STAppData;


HashMap<String, Object> prefs;
    public DriverFactory(SeleniumTestToolData in_STAppData)
    {
        STAppData = in_STAppData;
        this.targetbrowser = STAppData.TargetBrowser;
  this.waitForLoad = STAppData.waitForLoad;
  this.promptBehavior = STAppData.promptBehavior;
  this.OSType = STAppData.OSType;
      
       
         BrowsermatorAppFolder =   System.getProperty("user.home")+File.separator+"BrowsermatorAppFolder"+File.separator;
          WEBDRIVERSDIR = BrowsermatorAppFolder + "Webdrivers" + File.separator;
           this.firefox_path = appConfig.getKeyValue("Firefox");
  this.chrome_path = appConfig.getKeyValue("Chrome 49");
  this.chrome_main_path = appConfig.getKeyValue("Chrome");
  this.downloadDir = appConfig.getKeyValue("downloaddir");
    this.firefox_path = appConfig.getKeyValue("Firefox");
  this.chrome_path = appConfig.getKeyValue("Chrome 49");
  this.chrome_main_path = appConfig.getKeyValue("Chrome");
  this.downloadDir = appConfig.getKeyValue("downloaddir");
     prefs = new HashMap<String, Object>();
      this.targetbrowser = STAppData.TargetBrowser;
  this.waitForLoad = STAppData.waitForLoad;
  this.OSType = STAppData.OSType;
  switch (waitForLoad)
   {
       case "Yes":
           PageLoadConstant = PageLoadStrategy.NORMAL;
           stringPageLoadConstant = "normal";
           break;
       case "No":
           PageLoadConstant = PageLoadStrategy.NONE;
           stringPageLoadConstant = "none";
           break;
       case "Local DOM Only":
           PageLoadConstant = PageLoadStrategy.EAGER;
           stringPageLoadConstant = "eager";
           break;
   }
    }
    public WebDriver buildDriver()
    {
         File thisDriver =  new File( WEBDRIVERSDIR+"geckodriver-win32"+File.separator+"geckodriver.exe");
  System.setProperty ("webdriver.chrome.disableBuildCheck", "true");
    switch (promptBehavior)
   {
       case "Dismiss":
           promptBehaviorConstant = UnexpectedAlertBehaviour.DISMISS;
          
           break;
       case "Accept":
         promptBehaviorConstant = UnexpectedAlertBehaviour.ACCEPT;
         
           break;
       case "Fail":
             promptBehaviorConstant = UnexpectedAlertBehaviour.IGNORE;
           break;
   }
   switch (waitForLoad)
   {
       case "Yes":
           PageLoadConstant = PageLoadStrategy.NORMAL;
           stringPageLoadConstant = "normal";
           break;
       case "No":
           PageLoadConstant = PageLoadStrategy.NONE;
           stringPageLoadConstant = "none";
           break;
       case "Local DOM Only":
           PageLoadConstant = PageLoadStrategy.EAGER;
           stringPageLoadConstant = "eager";
           break;
   }
    switch (targetbrowser)
   {
     
     case "Firefox-Marionette":
     // legacy file support
         
         if ("Windows".equals(OSType))
     {
           thisDriver =  new File(WEBDRIVERSDIR+"geckodriver-win32"+File.separator+"geckodriver.exe");
          setPermissions(thisDriver);
        System.setProperty("webdriver.gecko.driver", thisDriver.getAbsolutePath());  
    //   System.setProperty("webdriver.gecko.driver", "lib"+File.separator+"geckodriver-win32"+File.separator+"geckodriver.exe");
     }   
     if ("Windows32".equals(OSType))
     {
         thisDriver =  new File(WEBDRIVERSDIR+"geckodriver-win32"+File.separator+"geckodriver.exe");
          setPermissions(thisDriver);
        System.setProperty("webdriver.gecko.driver", thisDriver.getAbsolutePath());   
      // System.setProperty("webdriver.gecko.driver", "lib"+File.separator+"geckodriver-win32"+File.separator+"geckodriver.exe");
     }
     if ("Windows64".equals(OSType))
     {
         thisDriver =  new File(WEBDRIVERSDIR+"geckodriver-win64"+File.separator+"geckodriver.exe");
         setPermissions(thisDriver);
        System.setProperty("webdriver.gecko.driver", thisDriver.getAbsolutePath());  
    //   System.setProperty("webdriver.gecko.driver", "lib"+File.separator+"geckodriver-win64"+File.separator+"geckodriver.exe");
     }
     if ("Mac".equals(OSType))
     {
            thisDriver =  new File(WEBDRIVERSDIR+"geckodriver-osx"+File.separator+"geckodriver");
          setPermissions(thisDriver);
        System.setProperty("webdriver.gecko.driver", thisDriver.getAbsolutePath());     
    //  System.setProperty("webdriver.gecko.driver", "lib"+File.separator+"geckodriver-osx"+File.separator+"geckodriver");
     }
     if ("Linux-32".equals(OSType))
     {
              thisDriver =  new File(WEBDRIVERSDIR+"geckodriver-linux32"+File.separator+"geckodriver");
          setPermissions(thisDriver);
        System.setProperty("webdriver.gecko.driver", thisDriver.getAbsolutePath());  
    //  System.setProperty("webdriver.gecko.driver", "lib"+File.separator+"geckodriver-linux32"+File.separator+"geckodriver");
     }
     if ("Linux-64".equals(OSType))
     {
                thisDriver =  new File(WEBDRIVERSDIR+"geckodriver-linux64"+File.separator+"geckodriver");
          setPermissions(thisDriver);
        System.setProperty("webdriver.gecko.driver", thisDriver.getAbsolutePath());  
   //   System.setProperty("webdriver.gecko.driver", "lib"+File.separator+"geckodriver-linux64"+File.separator+"geckodriver");
     }
   
    if (firefox_path!=null) {
        System.setProperty("webdriver.firefox.bin", firefox_path);
    }

    try
    {


        FirefoxOptions options = new FirefoxOptions();
    
        if (!"".equals(downloadDir))
        {
                FirefoxProfile profile = new FirefoxProfile();
profile.setPreference("browser.download.dir", downloadDir);
profile.setPreference("browser.download.folderList", 2);
profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "...");
    options.setProfile(profile);
        }        
options.addPreference("dom.webnotifications.enabled", false);
        options.setUnhandledPromptBehaviour(promptBehaviorConstant);
       options.setPageLoadStrategy(PageLoadConstant);
   
        driver = new FirefoxDriver(options);

    }
    catch (Exception ex)
    {
        System.out.println ("Exception launching Marionette driver... possibly XP or missing msvcr110.dll: " + ex.toString());
     
         Prompter fallbackprompt = new Prompter ("Driver Error", "Could not launch Marionette driver:" + ex.toString(), false,0,0);
     
         
          
    }
      
     break;
            
    case "Firefox":
   
     if ("Windows".equals(OSType))
     {
        
        thisDriver =  new File(WEBDRIVERSDIR+"geckodriver-win32"+File.separator+"geckodriver.exe");
          setPermissions(thisDriver);
        System.setProperty("webdriver.gecko.driver", thisDriver.getAbsolutePath());  
     }
     if ("Windows32".equals(OSType))
     {
         thisDriver =  new File(WEBDRIVERSDIR+"geckodriver-win32"+File.separator+"geckodriver.exe");
          setPermissions(thisDriver);
        System.setProperty("webdriver.gecko.driver", thisDriver.getAbsolutePath());  
     }
     if ("Windows64".equals(OSType))
     {
           thisDriver =  new File(WEBDRIVERSDIR+"geckodriver-win64"+File.separator+"geckodriver.exe");
          setPermissions(thisDriver);
        System.setProperty("webdriver.gecko.driver", thisDriver.getAbsolutePath());  
   //    System.setProperty("webdriver.gecko.driver", BMPATH+File.separator+"lib"+File.separator+"geckodriver-win64"+File.separator+"geckodriver.exe");
     }
     if ("Mac".equals(OSType))
     {
            thisDriver =  new File(WEBDRIVERSDIR+"geckodriver-osx"+File.separator+"geckodriver");
          setPermissions(thisDriver);
        System.setProperty("webdriver.gecko.driver", thisDriver.getAbsolutePath());  
  //    System.setProperty("webdriver.gecko.driver", BMPATH+File.separator+"lib"+File.separator+"geckodriver-osx"+File.separator+"geckodriver");
     }
     if ("Linux-32".equals(OSType))
     {
            thisDriver =  new File(WEBDRIVERSDIR+"geckodriver-linux32"+File.separator+"geckodriver");
          setPermissions(thisDriver);
        System.setProperty("webdriver.gecko.driver", thisDriver.getAbsolutePath());  
  //    System.setProperty("webdriver.gecko.driver",BMPATH+File.separator+ "lib"+File.separator+"geckodriver-linux32"+File.separator+"geckodriver");
     }
     if ("Linux-64".equals(OSType))
     {
           thisDriver =  new File(WEBDRIVERSDIR+"geckodriver-linux64"+File.separator+"geckodriver");
          setPermissions(thisDriver);
        System.setProperty("webdriver.gecko.driver", thisDriver.getAbsolutePath());  
    //  System.setProperty("webdriver.gecko.driver", BMPATH+File.separator+"lib"+File.separator+"geckodriver-linux64"+File.separator+"geckodriver");
     }
   
    if (firefox_path!=null) {
        System.setProperty("webdriver.firefox.bin", firefox_path);
    }

    try
    {

         FirefoxOptions options = new FirefoxOptions();
         
            if (!"".equals(downloadDir))
            {
                   FirefoxProfile profile = new FirefoxProfile();
                   
profile.setPreference("browser.download.dir", downloadDir);
profile.setPreference("browser.download.folderList", 2);
profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "...");
    options.setProfile(profile);
            }
            // This line allows firefox to launch but session still not created... Selenium 4 quirk..
            //       options.setCapability("marionette", false);
     
         options.addPreference("dom.webnotifications.enabled", false);
         options.setUnhandledPromptBehaviour(promptBehaviorConstant);
        options.setPageLoadStrategy(PageLoadConstant);

        driver = new FirefoxDriver(options);
       

    }
    catch (Exception ex)
    {
        System.out.println ("Exception launching Marionette driver... possibly XP or missing msvcr110.dll: " + ex.toString());
     
         Prompter fallbackprompt = new Prompter ("Driver Error", "Could not launch the Marionette driver: " + ex.toString(), false,0,0);
      
      
    }
      
     break;
     
     case "Silent Mode (HTMLUnit)":
  //not implemented yet
//   DesiredCapabilities capabilities = DesiredCapabilities.htmlUnit();
//  capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, promptBehaviorConstant);
//   capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, PageLoadConstant);
  try{
         driver = new HtmlUnitDriver();  

    }
    catch (Exception ex)
    {
        System.out.println ("Exception launching HTMLUnit driver... possibly XP or missing msvcr110.dll: " + ex.toString());
     
         Prompter fallbackprompt = new Prompter ("Driver Error", "Could not launch the Marionette driver: " + ex.toString(), false,0,0);
      
      
    }

     break;
     case "Chrome":

 
          ChromeOptions options = new ChromeOptions();  
         if (chrome_main_path!=null)
         {
          options.setBinary(chrome_main_path);
         }
        
          options.setUnhandledPromptBehaviour(promptBehaviorConstant);             
                 options.setPageLoadStrategy(PageLoadConstant); 
                 options.addArguments("--dns-prefetch-disable");
                 prefs.put("profile.default_content_setting_values.notifications", 2);
             
                // prefs.put("--dns-prefetch-disable", );
                 if (!"".equals(downloadDir))
                 {
                 prefs.put("download.default_directory", downloadDir);
                 }
                 options.setExperimentalOption("prefs", prefs);
  
         if ("Windows".equals(OSType))
     {
            thisDriver =  new File(WEBDRIVERSDIR+"chromedriver_win32"+File.separator+"chromedriver.exe");
          setPermissions(thisDriver);
        System.setProperty("webdriver.chrome.driver", thisDriver.getAbsolutePath());  
     //   System.setProperty("webdriver.chrome.driver", BMPATH+File.separator+"lib"+File.separator+"chromedriver_win32"+File.separator+"chromedriver.exe");
     }
     if ("Windows32".equals(OSType))
     {
                 thisDriver =  new File(WEBDRIVERSDIR+"chromedriver_win32"+File.separator+"chromedriver.exe");
          setPermissions(thisDriver);
        System.setProperty("webdriver.chrome.driver", thisDriver.getAbsolutePath());  
 // System.setProperty("webdriver.chrome.driver", BMPATH+File.separator+"lib"+File.separator+"chromedriver_win32"+File.separator+"chromedriver.exe");
 
  
     }
       if ("Windows64".equals(OSType))
     {
                 thisDriver =  new File(WEBDRIVERSDIR+"chromedriver_win32"+File.separator+"chromedriver.exe");
          setPermissions(thisDriver);
        System.setProperty("webdriver.chrome.driver", thisDriver.getAbsolutePath());  
   //  System.setProperty("webdriver.chrome.driver", BMPATH+File.separator+"lib"+File.separator+"chromedriver_win32"+File.separator+"chromedriver.exe");
     }
     if ("Mac".equals(OSType))
     {
                 thisDriver =  new File(WEBDRIVERSDIR+"chromedriver_mac64"+File.separator+"chromedriver");
          setPermissions(thisDriver);
        System.setProperty("webdriver.chrome.driver", thisDriver.getAbsolutePath());  
   //  System.setProperty("webdriver.chrome.driver", BMPATH+File.separator+"lib"+File.separator+"chromedriver_mac64"+File.separator+"chromedriver");
     }
     if ("Linux-32".equals(OSType))
     {
                  thisDriver =  new File(WEBDRIVERSDIR+"chromedriver_linux32"+File.separator+"chromedriver");
          setPermissions(thisDriver);
        System.setProperty("webdriver.chrome.driver", thisDriver.getAbsolutePath());    
   //  System.setProperty("webdriver.chrome.driver",BMPATH+File.separator+ "lib"+File.separator+"chromedriver_linux32"+File.separator+"chromedriver");
     }
     if ("Linux-64".equals(OSType))
     {
                    thisDriver =  new File(WEBDRIVERSDIR+"chromedriver_linux64"+File.separator+"chromedriver");
          setPermissions(thisDriver);
        System.setProperty("webdriver.chrome.driver", thisDriver.getAbsolutePath());  
   //  System.setProperty("webdriver.chrome.driver", BMPATH+File.separator+"lib"+File.separator+"chromedriver_linux64"+File.separator+"chromedriver");
     }
     try
     {
        driver = new ChromeDriver(options);     
     }
   catch (Exception ex)
   {
       System.out.println ("Problem launching Chromedriver: " + ex.toString());
     
         Prompter fallbackprompt = new Prompter ("Driver Error", "Could not launch the Chromedriver." + ex.toString(), false,0,0);
  
   }
  
      break;
     
     
   case "Chrome 49":
         ChromeOptions options49 = new ChromeOptions();
                 prefs.put("profile.default_content_setting_values.notifications", 2);
        //         options49.setExperimentalOption("prefs", prefs);     
      if (chrome_path!=null) {
   
          
     ChromeOptions setBinary = options49.setBinary(chrome_path);


    }
      // since new SElenium does not work with Chrome49, this was changed
       //          thisDriver =  new File(WEBDRIVERSDIR+"chromedriver_win32"+File.separator+"chromedriver-winxp.exe");
                 thisDriver =  new File(WEBDRIVERSDIR+"chromedriver_win32"+File.separator+"chromedriver.exe");
      
                 setPermissions(thisDriver);
        System.setProperty("webdriver.chrome.driver", thisDriver.getAbsolutePath());  
    // System.setProperty("webdriver.chrome.driver", BMPATH+File.separator+"lib"+File.separator+"chromedriver_win32"+File.separator+"chromedriver-winxp.exe");
   
    
     try
     {
        driver = new ChromeDriver(options49);     
     }
   catch (Exception ex)
   {
       System.out.println ("Problem launching Chromedriver 49: " + ex.toString());
        Prompter fallbackprompt = new Prompter ("Driver Error", "Could not launch the Chrome 49 driver." + ex.toString(), false,0, 0);
          
   }
 
   
     break;
  case "Edge":
     
       String windir = System.getenv("windir");
                boolean is64bit = false;
    
    is64bit = (System.getenv("ProgramFiles(x86)") != null);
  String edgeDriverPath = windir + "\\SysWOW64\\MicrosoftWebDriver.exe";
        if (!is64bit)
        {
     edgeDriverPath = windir + "\\System32\\MicrosoftWebDriver.exe";
        }
       
  System.setProperty("webdriver.edge.driver", edgeDriverPath); 
     EdgeOptions edgeOptions = new EdgeOptions();
   
      edgeOptions.setPageLoadStrategy(PageLoadConstant);
      // edgeOptions.setUnhandledPromptBehaviour... to be implemented?
         try {
         
            driver = new EdgeDriver(edgeOptions);
   }
     catch (Exception ex)
   {
       System.out.println ("Problem launching EdgeDriver: " + ex.toString());
        Prompter fallbackprompt = new Prompter ("Driver Error", "Could not launch the Edge Driver. Location: "+edgeDriverPath+" Go to Settings > Update and Security > For Developer and then select Developer mode." + ex.toString(), false,0, 0);
    
   }
             
       break;
    
    }
    return driver;
   }
      public void setPermissions(File thisDriver)
         {
             
        
           if (!thisDriver.canExecute())
           {
       Prompter cantexecuteprompt = new Prompter ("Permissions Error", "The current user does not have permission to run the webdriver.  The Browsermator will attempt to set permissions now.  If this fails, you'll need to manually set permission to execute the following file: " + thisDriver.getAbsolutePath(), false,0,0);     
  
            thisDriver.setExecutable (true, true);
           }
           
         }
}
