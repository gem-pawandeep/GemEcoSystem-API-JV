Feature: GemEcoSystem-APIs-JV-BDD

  @Hello
  Scenario: test
    Given test

  Scenario Outline:Sample-Test
    Given Set endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Gettt    | get    | 200             |
      |   hello| hello       | 300     |

@bye
  Scenario Outline:Get Company
    Given Set endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Getc     | get    | 201             |
@regression @hello
  Scenario Outline: Validate UserName
    Given Set endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Gett     | get    | 200             |

  Scenario Outline: Validate Username when username does not exists
    Given Set endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Gett2    | get    | 200             |

  @Hello
  Scenario Outline: Get data of suite s-run id not present
    Given Authenticate endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Gettte   | get    | 400             |

  Scenario Outline: Get Token
    Given Set token endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Gettoken | get    | 200             |

  Scenario Outline: Get Token With Wrong Authentication
    Given Set Wrong token endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Gettoken | get    | 403             |

  @regression
  Scenario Outline: Get Token With Empty Authentication
    Given Set Empty token endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Gettoken | get    | 403             |

  Scenario Outline: Login User
    Given Set endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName       |
      | Login    | Post   | 200             | Login_sampleJson |

  Scenario Outline: Login User with wrong credentials
    Given Set credentials endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | Login    | Post   | 400             | Login2_sampleJson |

  Scenario Outline: Login User with Empty Fields
    Given Set credentials endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | Login    | Post   | 400             | Login3_sampleJson |

  #Scenario Outline: Login User with Empty Body
  #  Given Set credentials endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
  #  Then Verify Status code <Expected_status>
  #  Examples:
  #    | endpoint | Method | Expected_status | SampleName        |
  #    | Login    | Post   | 500             | Login4_sampleJson |

  Scenario Outline: Change Token
    Given Set token endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status |
      | posttoken | Post   | 200             |

  Scenario Outline: Change Token When Header is not Given
    Given Set Empty token endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status |
      | posttoken | Post   | 403             |

  Scenario Outline: Change Token wih wrong Authentication
    Given Set Wrong token endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status |
      | posttoken | Post   | 403             |

  Scenario Outline: Insert Test-Suite Using Post APIs
    Given Set Suite-API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | pospo    | Post   | 201             | psuite_sampleJson |

  Scenario Outline: Insert the suite using Post API when S-run-id is already present
    Given Set Suite-API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName         |
      | pospo    | Post   | 400             | psuite1_sampleJson |

  Scenario Outline: Insert the Suite using post API when S-run-id not present in the payload
    Given Set Suite-API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName         |
      | pospo    | Post   | 201             | psuite2_sampleJson |

  Scenario Outline: Insert the Suite using Wrong Authentication
    Given Set Suite-API using Wrong Authentication endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName         |
      | pospo    | Post   | 403             | psuite2_sampleJson |

  Scenario Outline: Insert the Suite when Headers not given
    Given Set Suite-API when headers not given endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName         |
      | pospo    | Post   | 400             | psuite2_sampleJson |

  Scenario Outline: Update the suite using Put API
    Given Update Suite using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName       |
      | putu     | put    | 200             | put_1_sampleJson |

  Scenario Outline: Update the suite When S-run-id is not present in Database
    Given Update Suite when S-run-id not present using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName       |
      | putu     | put    | 400             | put_2_sampleJson |

  Scenario Outline: Update the suite Using Wrong Authentication
    Given Update Suite using wrong Authentication using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName       |
      | putu     | put    | 403             | put_2_sampleJson |

  Scenario Outline: Update the suite without Authentication
    Given Update Suite without Authentication using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName       |
      | putu     | put    | 400             | put_2_sampleJson |

  Scenario Outline:Create new record for Testcases
    Given Create record using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName       |
      | pospos   | post   | 201             | ptest_sampleJson |

  Scenario Outline:Create new record for Testcases when S-run-id not exists in database
    Given Create record when s-id not exists using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | pospos   | post   | 400             | ptest1_sampleJson |

  Scenario Outline:Create new record for Testcases when S-run-id not given
    Given Create record when s-id not given using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | pospos   | post   | 400             | ptest2_sampleJson |

  Scenario Outline:Create new record for Testcases when TC-run-id not given
    Given Create record when TC-id not given using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | pospos   | post   | 201             | ptest3_sampleJson |

  Scenario Outline:Create new record for Testcases with wrong Authentication
    Given Create record with wrong Authentication using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | pospos   | post   | 400             | ptest3_sampleJson |

  Scenario Outline:Create new record for Testcases when authentication not given
    Given Create record when authentication not given using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | pospos   | post   | 400             | ptest3_sampleJson |

  Scenario Outline: Update the suite using Put API
    Given Update Suite type2 using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | putexe   | put    | 200             | puter1_sampleJson |

  Scenario Outline: Update the Testcase data when S-run-id not given
    Given Update Suite without s-runid using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | putexe   | put    | 400             | puter2_sampleJson |

  Scenario Outline: Update the suite when TC-id not given
    Given Update Suite without tc-id using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | putexe   | put    | 400             | puter3_sampleJson |

  Scenario Outline: Update the testcase data using wrong authentication
    Given Update Suite using wrong Authentication using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | putexe   | put    | 403             | puter3_sampleJson |

  Scenario Outline: Create New User
    Given  Set Suite-API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName            |
      | Post     | Post   | 201             | Postgemini_sampleJson |

  Scenario Outline: Create New User Already Exists
    Given  Set Suite-API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName             |
      | Post     | Post   | 409             | Postgemini2_sampleJson |

  Scenario Outline: Create New User When Compulsory field not present
    Given  Set Suite-API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName             |
      | Post     | Post   | 500             | Postgemini3_sampleJson |

  Scenario Outline: Create New User When giving empty body
    Given  Set Suite-API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName             |
      | Post     | Post   | 500             | Postgemini4_sampleJson |

  Scenario Outline: Create New User When giving wrong E-mail
    Given  Set Suite-API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName             |
      | Post     | Post   | 400             | Postgemini5_sampleJson |

  Scenario Outline: Sample-2
    Given Set endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Gettt    | get    | 200             |

  Scenario Outline: Get data of suite s-run id not present - 2
    Given Authenticate endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Gettte   | get    | 400             |

  Scenario Outline: Get data of test case
    Given Set endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Gettest  | get    | 200             |

  Scenario Outline:Get data of Testcase TC-run-id not valid
    Given Authenticate endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Gettest2 | get    | 400             |

######################## Bucket APIs-1 ###################################
  Scenario Outline:Deleting the Files....
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint        | Method | Expected_status | SampleName                 | stepName                               |
      | permanentDelete | Post   | 200             | permanentDelete_sampleJson | Test to Deleting the Files Permanently |

  Scenario Outline: File upload with Correct BridgeToken
    Given Set endpoint "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload1 | 200             |

  Scenario Outline: File upload with incorrect tag
    Given Set endpoint "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload2 | 400             |

  Scenario Outline: File upload with incorrect BridgeToken
    Given Set endpoint with incorrect bridgetoken "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload1 | 403             |

  Scenario Outline:Deleting the File.
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint        | Method | Expected_status | SampleName                 | stepName                               |
      | permanentDelete | Post   | 200             | permanentDelete_sampleJson | Test to Deleting the Files Permanently |


  Scenario Outline: File upload with Bearer token
    Given Set endpoint without username "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload1 | 200             |

  Scenario Outline: File upload with Bearer token is not present in username
    Given Set endpoint without username in Bearer Token "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload1 | 403             |

  Scenario Outline: File upload when username is not present in the DataBase
    Given Set endpoint with username not present in db "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload1 | 403             |

######################## Bucket APIs-2 ###################################

  Scenario Outline: File upload by text
    Given Set endpoint with text "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload3 | 200             |

  Scenario Outline: File upload by text with incorrect tag
    Given Set endpoint with text wrong tag "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload4 | 400             |

  Scenario Outline: File upload by text with wrong bridge token
    Given Set endpoint with text with wrong BridgeToken "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload3 | 403             |

  Scenario Outline: File upload by text when Bearer token is not present in username
    Given Set endpoint with text when BT is not present "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload3 | 403             |

  Scenario Outline: File upload by text when username is not present in DB
    Given Set endpoint with text without valid username "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload3 | 403             |

  Scenario Outline: File upload by text without permission
    Given Set endpoint with text wrong tag "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload5 | 401             |

  Scenario Outline: File upload by text without write access
    Given Set endpoint with text without write access "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload6 | 401             |

######################## Bucket APIs-3 ###################################
  ################################have to call this#######################
  Scenario Outline:Get file back from recycle bin
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status | SampleName           | stepName                     |
      | changeTag | Post   | 200             | publicTag_sampleJson | Test to change tag to public |
  ########################################################################

  Scenario Outline:Give WRITE access of the files to the users
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        | stepName                          |
      | access   | Post   | 200             | Access_sampleJson | Test to give write access to user |

  Scenario Outline:Give READ access of the files to the users
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        | stepName                          |
      | access1  | Post   | 200             | Access_sampleJson | Test to give write access to user |

  Scenario Outline:Remove WRITE access of the files to the users
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        | stepName                          |
      | access2  | Post   | 200             | Access_sampleJson | Test to give write access to user |

  Scenario Outline:Remove READ access of the files to the users
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        | stepName                          |
      | access3  | Post   | 200             | Access_sampleJson | Test to give write access to user |

  Scenario Outline:Give WRITE access of the files to the users when mode provided is incorrect
    Given Post Suite-API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | access4  | Post   | 400             | Access_sampleJson |

  Scenario Outline:Remove READ access of the files to the users when the type provided is incorrect
    Given Post Suite-API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | access5  | Post   | 400             | Access_sampleJson |

  Scenario Outline:Give WRITE access of the files to the users when Bridge Token is not valid
    Given Set Post API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | access   | Post   | 403             | Access_sampleJson |

  Scenario Outline:Give WRITE access of the files to the users when username is not found in Bearer token
    Given Set Post API without BT endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | access   | Post   | 403             | Access_sampleJson |

    ######################## Bucket APIs-4 ###################################

  Scenario Outline:Give access of the folder to the users
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint     | Method | Expected_status | SampleName              | stepName                           |
      | folderAccess | Post   | 200             | folderAccess_sampleJson | Test to give folder access to user |

  Scenario Outline:Remove access of the folder from the users
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint      | Method | Expected_status | SampleName              | stepName                             |
      | folderAccess1 | Post   | 200             | folderAccess_sampleJson | Test to remove folder access to user |

  Scenario Outline:Remove access of the folder from the users when type provided is incorrect
    Given Post Suite-API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint      | Method | Expected_status | SampleName              |
      | folderAccess2 | Post   | 400             | folderAccess_sampleJson |

  Scenario Outline:Give access of the folder to the users when Bridge Token is not valid
    Given Set Post API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint     | Method | Expected_status | SampleName              |
      | folderAccess | Post   | 403             | folderAccess_sampleJson |

  Scenario Outline:Give access of the folder to the users when username is not found in Bearer token
    Given Set Post API without BT endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint     | Method | Expected_status | SampleName              |
      | folderAccess | Post   | 403             | folderAccess_sampleJson |

     ######################## Bucket APIs-5 ###################################

  Scenario Outline:Change tag to public
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status | SampleName           | stepName                     |
      | changeTag | Post   | 200             | publicTag_sampleJson | Test to change tag to public |

  Scenario Outline:Change tag to private
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status | SampleName            | stepName                      |
      | changeTag | Post   | 200             | privateTag_sampleJson | Test to change tag to private |


  Scenario Outline:Change tag to public when Bridge Token is not valid
    Given Set Post API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status | SampleName           |
      | changeTag | Post   | 403             | publicTag_sampleJson |

  Scenario Outline:Change tag to public when username is not found in Bearer token
    Given Set Post API without BT endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status | SampleName           |
      | changeTag | Post   | 403             | publicTag_sampleJson |

 #Scenario Outline:Change tag to public when tag provided is incorrect
 #  Given Post Suite-API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
 #  Then Verify Status code <Expected_status>
 #  Examples:
 #    | endpoint  | Method | Expected_status | SampleName            |
 #    | changeTag | Post   | 400             | publicTag2_sampleJson |

    ######################## Bucket APIs-6 ###################################

  Scenario Outline:Moving the files to the recycle bin
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status | SampleName           | stepName                                    |
      | deleteTag | Post   | 200             | deleteTag_sampleJson | Test to Moving the files to the recycle bin |

  Scenario Outline:Moving the files to the recycle bin when Bridge Token is not valid
    Given Set Post API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status | SampleName           |
      | deleteTag | Post   | 403             | deleteTag_sampleJson |

  Scenario Outline:Moving the files to the recycle bin when username is not found in Bearer token
    Given Set Post API without BT endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status | SampleName           |
      | deleteTag | Post   | 403             | deleteTag_sampleJson |

 #Scenario Outline:Moving the files to the recycle bin when tag provided is incorrect
 #  Given Post Suite-API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
 #  Then Verify Status code <Expected_status>
 #  Examples:
 #    | endpoint  | Method | Expected_status | SampleName            |
 #    | deleteTag | Post   | 400             | deleteTag2_sampleJson |

      ######################## Bucket APIs-7 ###################################

  Scenario Outline:Get the file from recycle bin
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status | SampleName            | stepName                              |
      | deleteTag | Post   | 200             | deleteTag3_sampleJson | Test to Get the file from recycle bin |


  Scenario Outline:Deleting the Files Permanently
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint        | Method | Expected_status | SampleName                 | stepName                               |
      | permanentDelete | Post   | 200             | permanentDelete_sampleJson | Test to Deleting the Files Permanently |

  Scenario Outline:Deleting the Files Permanently when Bridge Token is not valid
    Given Set Post API endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint        | Method | Expected_status | SampleName                 |
      | permanentDelete | Post   | 403             | permanentDelete_sampleJson |

  Scenario Outline:Deleting the Files Permanently when username is not found in Bearer token
    Given Set Post API without BT endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint        | Method | Expected_status | SampleName                 |
      | permanentDelete | Post   | 403             | permanentDelete_sampleJson |

       ######################## Bucket APIs-8 ###################################
  Scenario Outline: File upload before moving to recycle bin
    Given Set endpoint "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload1 | 200             |

  Scenario Outline:Moving the files to the recycle bin before getting
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status | SampleName           | stepName                                    |
      | deleteTag | Post   | 200             | deleteTag_sampleJson | Test to Moving the files to the recycle bin |


  Scenario Outline:Get the Files which are in recycle bin
    Given Set token endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint      | Method | Expected_status |
      | getrecyclebin | get    | 200             |

  Scenario Outline:Get the Files which are in recycle bin when Bridge Token is not valid
    Given Set Wrong token endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint      | Method | Expected_status |
      | getrecyclebin | get    | 403             |

  Scenario Outline:Get the Files which are in recycle bin when username is not found in Bearer token
    Given Set endpoint and method without bearer token "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint      | Method | Expected_status |
      | getrecyclebin | get    | 403             |

  Scenario Outline:Deleting the Files.
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint        | Method | Expected_status | SampleName                 | stepName                               |
      | permanentDelete | Post   | 200             | permanentDelete_sampleJson | Test to Deleting the Files Permanently |


  Scenario Outline:Get the Files when there is no file in recycle bin
    Given Setting token endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint      | Method | Expected_status |
      | getrecyclebin | get    | 404             |

       ######################## Bucket APIs-9 ###################################

  Scenario Outline: File upload.
    Given Set endpoint "<endpoint>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint    | Expected_status |
      | fileUpload1 | 200             |

# Scenario Outline:Get the Files when files are public
#   Given Get file by setting endpoint and method "<endpoint>" and "<Method>"
#   Then Verify Status code <Expected_status>
#   Examples:
#     | endpoint | Method | Expected_status |
#     | getFile  | get    | 200             |

  Scenario Outline:Change tag to private before get files
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status | SampleName            | stepName                      |
      | changeTag | Post   | 200             | privateTag_sampleJson | Test to change tag to private |

  Scenario Outline:Get the Files when it is private with Authentication
    Given Get file by setting Authentication: endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | getFile  | get    | 200             |

###########################################################

  Scenario Outline:Get the Files when username is not found in token and file is private.
    Given Get file by setting endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | getFile  | get    | 403             |

  Scenario Outline:Moving the files to the recycle bin before get file.
    Given Post Suite-API endpoint and method and SampleName and step "<endpoint>" and "<Method>" and "<SampleName>" and "<stepName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint  | Method | Expected_status | SampleName           | stepName                                    |
      | deleteTag | Post   | 200             | deleteTag_sampleJson | Test to Moving the files to the recycle bin |

  Scenario Outline:Get the Files when file is in recycle bin.
    Given Get file by setting Authentication, endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | getFile  | get    | 404             |

  Scenario Outline:Get the Files when file not found
    Given Get file by setting Authentication, endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | getFile  | get    | 404             |

  Scenario Outline:Get the Files when file is private and user not have permission to see it.
    Given Get file by setting Authentication, endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | getFile  | get    | 404             |