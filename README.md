xkcd-comics-downloader-and-viewer
=================================

This is just something I've been working on to download and view the XKCD comics off of xkcd.com

XkcdCrawler Class:
	So, what this does is, it just downloads all the web pages from xkcd.com and saves them as html files in the folder XKCD. It's pretty straight forward.

xkcd_img_parser:
	So, this is a piece of code I've written for the lex tool. It just scans all the html files and writes the hotlink for the comic images, to a file called xkcdImgs.txt.

xkcd_title_parser:
	This is pretty similar to the image parser, except, it extracts the titles of all the comics from the html files and saves them in a file called xkcdTitles.txt.

XkcdImageDownloader Class:
	This class reads links from the xkcdImgs.txt file and downloads the images using the BufferedImage class and the ImageIO class. The downloaded images are stored in the Images folder in png format.

Comic Class:
	This represents a single Comic. It has a title and the pathname to the corresponding image file.

XkcdTitleMapper Class:
	This class reads all the Titles from the xkcdTitles.txt file and maps them to the image files. The Comic class is used here.

XkcdComicBrowser Class:
	This class helps to browse the comics. It just displays the comics in a frame and you can browse to different ones by hitting the arrow keys.


This project isn't complete obviously... I've added the main method to each Class file. I didn't really do any design at all! I just wrote all the different modules up without thinking about the other. I intend to change that. Or if things work out well just the way they are, I'll keep it that way. So, that's it...
