@ui
Feature: Validate DemoQA UI

  Scenario: open base URL
    Given get the launch url

  Scenario Outline: Fill up the form using excel reader
    When user fills the form from given sheetname "<SheetName>" and rownumber <RowNumber>
    Examples:
      | SheetName   | RowNumber |
      | FormDetails | 0         |

  Scenario: close base URL
    Given close the launch url




