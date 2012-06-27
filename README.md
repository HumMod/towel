# Towel

The rescue blanket for the maze of HumMod.

## Requirements
The only requirement is Java FX 2.0, which isn't yet released to OS X.  However, enterprising individuals can find developer versions on the [Java FX](http://docs.oracle.com/javafx/) site.

### Effective use of Towel
1. Download the ".jar" file called ["HumModSearch.jar"](https://github.com/downloads/HumMod/towel/HumModSearch.jar)
2. Open the file and click the "Open File" button 
  * locate your local copy of HumMod ("HumMod.exe").
  * your local copy and its associated files (folders labeled: "Display", "Structure", etc.) must be in the same folder
3. Once you have designated the apporpriate file, a message "Your file input has been successfully received" will display
4. Search away *(spelling matters)*

### How to search

When using Towel, there are two ways to search: common and global.
A common search locates medical measurements included in HumMod (ex: "blood volume" or "nerve activity").
When searching, all search queries must be spelled correctly, but are not case-sensitive.
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

### Pitfalls
- Spelling
- Too specific
- Too lengthy

### Created By
Towel was created and developed by Graham Husband, a freshman at Vanderbilt University, during the 2012 University of Mississippi Medical Center (UMC) Student Undergraduate Research Experience (SURE program). Graham completed his SURE under the mentorship of Dr. Robert Hester, Professor of Physiology and Head of the Center for Computational Medicine at UMC.

## License
Towel is open-sourced through HC Simulations, LLC under GPL 2.0.