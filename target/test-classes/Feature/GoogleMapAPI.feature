Feature: Validating place API's

@AddPlaceAPI
Scenario Outline: Verify if plce is being succesfully added using AddplaceAPI
  Given User addPlace Payload "<name>" "<phone>" "<address>"
  When  User calls "AddPlaceAPI" with "Post" Https request
  Then  The API call got success with static code 200
  And User check "status" in response body is "OK"
  And User check "scope" in response body is "APP"
  And User Verify place_id created maps to "<name>" using "GetPlaceAPI"
  
  
  Examples:
 |name         |phone      |address|
 |santhanakumar|00224455477|world trade centre|
 #|Ram          |87554458788|Rani Flats        |
 
 @DeletePlaceAPI
 Scenario: Verify if DeletePlace functionality is working
   Given User DeletePlace payload
   When  User calls "DeletePlaceAPI" with "Post" Https request
   Then  The API call got success with static code 200
   And   User check "status" in response body is "OK"