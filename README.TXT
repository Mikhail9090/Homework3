Execute testng.xml to run tests.
If you want to check tests in Chrome you should open testng.xml, find "browser" parameter and set "chrome."
For example: <parameter name="browser" value="chrome"/>
For Firefox you have to specify this parameter as "firefox".
For example: <parameter name="browser" value="firefox"/>