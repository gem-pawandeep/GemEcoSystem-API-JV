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