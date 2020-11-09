To get the app to log in, you need to set up a proxy for the URL: *https://api.speedx.com/api/v3.0/sign_in*

This URL will need to return a 200 with a JSON response of:

	{
	  "code": 0,
	  "result":{
		"sessionId":"93f39865-89eb-42c0-aa22-699b1b6335ba",
		"user_info":{
		  "userId":"bff9c970-3672-4ee9-99db-40a10b4c6d69",
		  "username":"myusername",
		  "nickname":"mynickname",
		  "sex":"1",
		  "weight":174.0,
		  "height":70.0,
		  "country":"",
		  "province":"",
		  "city":"",
		  "district":"",
		  "totalDistance":0.0,
		  "monthlyDistance":0.0,
		  "weeklyDistance":0.0,
		  "totalTime":0,
		  "lastestCyclingTime":0,
		  "avatar":"",
		  "edited":false,
		  "objectId":"83040fdf-250f-4375-9429-f8b8343b5871",
		  "gridNum":0,
		  "sameGrid":0,
		  "clubName":"",
		  "userIntId":123,
		  "updatedAt":"",
		  "createdAt":"",
		  "clubId":"",
		  "isOk":1,
		  "birthday":"659668971",
		  "remarks":"",
		  "followNum":0,
		  "followStatu":0,
		  "isFriend":false,
		  "fansNum":0,
		  "badgeNum":0,
		  "speedxId":123456,
		  "cardiacRate":186,
		  "mobile":"",
		  "achievement_count":0,
		  "training_id":0,
		  "training_type":0,
		  "level":0,
		  "ftp":0,
		  "geoCode":"",
		  "email":"myemail@example.com"
		}
	  }
	}

The GUIDs were just randomly generated. The app is expecting string values with a unique ID, so I guessed they would probably be GUIDs. For "sex", "0" = female, "1" = male. Height is in centimeters, weight is in kilograms, and birthday is an epoch date is seconds (seconds since Jan 1, 1970).

Having this response will allow you to enter any email and password and get into the app to pair with a bike and adjust it's settings. Unfortunately grabbing GPS activity files from the bike is a bit more involved (still working on that).

My recommendation is to

1. side-load the APK
2. [download Charles Proxy](https://www.charlesproxy.com/) on a computer on your LAN
3. set your phone to use Charles Proxy as it's Wifi Proxy server along with setting up HTTPS proxying and installing the Charles Proxy root CA to allow the HTTPS certificates to validate (if you've never done this, see [this](https://medium.com/@hackupstate/using-charles-proxy-to-debug-android-ssl-traffic-e61fc38760f7))
4. save the JSON above to a file with a ".json" extension on your local machine and set a "Map Local..." rule for *https://api.speedx.com/api/v3.0/sign_in* to serve that file

Note: If for some reason the "cardiacRate" is missing or 0, it'll drop the user off on a secondary registration screen after login to pick sex, height, weight, birthday, and cardiac rate again. It'll generate the cardiac rate from the other entered values. Clicking "save" on that screen requires proxying *https://api.speedx.com/api/v3.0/user/train* to return the JSON:

	{
	  "code": 0,
	  "result":{
		  "sex":"1",
		  "weight":174.0,
		  "height":70.0,
		  "cardiac_rate": 186,
		  "birthday": "659668971"
	  }
	}

Of course you won't have to deal with this is you have a cardiacRate in the *https://api.speedx.com/api/v3.0/sign_in* response, but I thought it would mention this since it is a bit of knowledge that might be useful.