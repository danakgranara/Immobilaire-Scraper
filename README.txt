to scrape immobilaire.it listings:
1. determine the area you will be searching for listings(the name of the location that comes after the last comma on each listing), type this in line 1 of scrapingLinks.txt
2. find your desired search results for a certain area
    paste the link for page one into line 2 of scrapingLinks.txt
    paste the link for page two into line 3 of scrapingLinks.txt
3. type the page at which you would like to start scraping on line 4 of scrapingLinks.txt
4. type the last page you would like to scrape into line 5 of scrapingLinks.txt
5. type the name of the text file you would like to write your results into on line 6 of scrapingLinks.txt
    the program will write each listing on each line of the file in the following format:(the location will be whatever comes before the last comma, either a neihgborhood or region of a city)
    location,price,numberOfRooms,squareMeters,link
6. run mainScanner.java
    if an error occurs at any point, note the last page scraped(see the terminal) and run the program again starting at the next page(by updating the page number on line 4). It will continue writing into the text file where you left off
    pages will run into errors if a certain listing is missing a certain number, such as number of rooms or square meters 