Feature: Stock Accounting Login Feature
Description: Login with feature will test login functionality
Scenario: Login with valid Usename and Password
Given Open Browser
When Enter the url "Https://webapp.qedge.com"
And Enter Username and Password
And Click on Login
Then must Login Successfully