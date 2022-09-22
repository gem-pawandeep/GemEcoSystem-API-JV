Feature: GemEcoSystem-APIs-JV

  Scenario Outline: Sample-Test
    Given Set endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Gettt    | get    | 200             |

  Scenario Outline: Get Company
    Given Set endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Getc     | get    | 200             |

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

  Scenario Outline: Login User with Empty Body
    Given Set credentials endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | Login    | Post   | 500             | Login4_sampleJson |

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
      | pospo    | Post   | 403             | psuite2_sampleJson |

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
      | putu     | put    | 403             | put_2_sampleJson |

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
      | pospos   | post   | 403             | ptest3_sampleJson |

  Scenario Outline:Create new record for Testcases when authentication not given
    Given Create record when authentication not given using endpoint and method and SampleName "<endpoint>" and "<Method>" and "<SampleName>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status | SampleName        |
      | pospos   | post   | 403             | ptest3_sampleJson |

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