package com.ehome.dbutils.bean;

public class NewsBean {
	private Long id;
	private int cfgId;				// 所属板块
	private String title;			// 新闻标题
	private String description;		// 新闻描述
	private String images;			// 新闻图片
	private String video;			// 新闻视频
	private String source;			// 新闻来源
	private int recommend;			// 是否推荐
	private int ishot;				// 是否热点
	private String thumb;			// 缩略图
	private String author;			// 发布人
	private Integer pubtime;		// 发布时间
	private Integer modifytime;		// 修改时间
	private int galleryId;			// 组图
	
	private int type;				// 新闻类别(0，无；1,视频；2，组图)
	private int commentCount;		// 评论数量
	private int articleId;			// 新闻详情
	
	
	// 新闻类别 
	// 0:无；1:视频；2:组图
	public final static int NONE = 0;		// 无
	public final static int VIDEO = 1;	// 视频
	public final static int IMAGE = 2;	// 组图
	// 是否推荐
	public final static int RECOMMEND_YES = 1;	// 是推荐新闻
	public final static int RECOMMEND_NOT = 0;	// 是推荐新闻
	// 是否热点
	public final static int ISHOT_YES = 1;	// 是热点新闻
	public final static int ISHOT_NOT = 0;	// 是热点新闻

	
	///////////////////////////////////////////////////////////////////////////////////////
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCfgId() {
		return cfgId;
	}
	public void setCfgId(int cfgId) {
		this.cfgId = cfgId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public int getIshot() {
		return ishot;
	}
	public void setIshot(int ishot) {
		this.ishot = ishot;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getPubtime() {
		return pubtime;
	}
	public void setPubtime(Integer pubtime) {
		this.pubtime = pubtime;
	}
	public Integer getModifytime() {
		return modifytime;
	}
	public void setModifytime(Integer modifytime) {
		this.modifytime = modifytime;
	}
	public int getGalleryId() {
		return galleryId;
	}
	public void setGalleryId(int galleryId) {
		this.galleryId = galleryId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	

	

}
