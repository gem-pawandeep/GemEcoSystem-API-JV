Feature: Testing

  @Hello
  Scenario: test
    Given test

  Scenario Outline:Sample-Test
    Given Set endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      |Gettt    | get    | 200             |

  @bye
  Scenario Outline:Get Company
    Given Set endpoint and method "<endpoint>" and "<Method>"
    Then Verify Status code <Expected_status>
    Examples:
      | endpoint | Method | Expected_status |
      | Getc     | get    | 201             |