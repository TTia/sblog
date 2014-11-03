package sblog.cucumber;

import java.util.List;

import org.openqa.selenium.WebElement;

public class Page {
	WebElement header, footer;
	List<WebElement> bannerLinks;
	public WebElement getFooter() {
		return footer;
	}
	public WebElement getHeader() {
		return header;
	}
	public List<WebElement> getBannerLinks() {
		return bannerLinks;
	}
	public void setFooter(WebElement footer) {
		this.footer = footer;
	}
	public void setHeader(WebElement header) {
		this.header = header;
	}
	public void setBannerLinks(List<WebElement> bannerLinks) {
		this.bannerLinks = bannerLinks;
	}
}
