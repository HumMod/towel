# Towel

A cross-platform tool to identify the paths available to reach panels that contain desired information.

## License
Towel is licensed through HC Simulations, LLC under GPL 2.0.

### About
Towel was created and developed by Graham Husband, a freshman at Vanderbilt University. Graham developed Towel
during his time at the Student Undergraduate Research Experience (SURE program), 2012, at the University Mississippi
Medical Center (UMMC). Graham developed this tool under Dr. Robert Hester, a professor of physiology at UMMC.

#### Help
To use Towel effectively, use the following instructions.

1. Download the ".jar" file called "HumModSearch.jar"
2. Open the file and click the "Open File" button 
  * locate your local copy of HumMod ("HumMod.exe").
  * your local copy and its associated files (folders labeled: "Display", "Structure", etc.) must be in the same folder
3. Once you have designated the apporpriate file, a message "Your file input has been successfully received" will display
4. Search away

##### How to search

When using Towel, there are two ways to search: common searches and global searches.
A common name search searches for medical measurements included in HumMod (ex: "blood volume" or "nerve activity").
When searching, all search queries must be spelt correctly, but are not case-sensitive.
These searches must use general diction. For instance, a search for "arterial blood pressure" would not yield any results,
but a search for "blood pressure" would offer the all potential panel pathways to reach blood pressure, including
arterial blood pressure. Generally, the data in HumMod are never displayed more than a few times, so these general 
searches should never be lengthy.

A global name search involves searching for the variable names used in HumMod itself (ex: "CellProtein.Degradation").
To search using global names, a period (".") must be included in the search query.
Any characters listed before the period are considered the Structure Name. Searching for just Structure Names
(ex: "CellProtein.") will yield a longer list. Anything after the period is considered the Variable Name. Searching for
the Structure and Variable names (ex: "CellProtein.Degradation") will yield the most specific and concise results Towel
can offer.