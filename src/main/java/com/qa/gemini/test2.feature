
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