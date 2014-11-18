package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页类
 * @author biao
 *
 */
public class PageBean {
	public static final int PER_PAGE = 10;

	/**
	 * 当前页
	 */
    private int curPage;
    /**
     * 最大页
     */
    private int maxPage;
    /**
     * 每页多少个
     */
    private int perPage;
    /**
     * 总共多少条数据
     */
    private long total;
    
    /*
     * 当前页附近多少页，例如当前为第10页，nearCount为5，那返回： 8，9，10，11，12
     */
    private int nearCount;
    
    
    /**
     * 默认获取当前页附近的10页
     * @param curPage 当前页
     * @param total 总数
     * @param perPage 每页多少条
     * @return
     */
    public static PageBean getInstance(Integer curPage, long total, int perPage){
    	return new PageBean(curPage, 5, perPage, total);
    }
    
    /**
     * 
     * @param curPage 当前页
     * @param total 总数
     * @param perPage 每页多少条
     * @param nearCount 当前页附近多少页，例如当前为第10页，nearCount为5，那返回： 8，9，10，11，12
     * @return
     */
    public static PageBean getInstance(Integer curPage, long total, int perPage, int nearCount){
    	return new PageBean(curPage, nearCount, perPage, total);
    }
    
    /**
     * 默认每页10个,获取附近的10页
     * @param curPage 当前页
     * @param total 总数
     * @return
     */
    public static PageBean getInstance(Integer curPage, long total){
    	return new PageBean(curPage, 5, 10, total);
    }
    
    /**
     * 
     * @param curPage
     * @param maxPage
     * @param nearCount
     * @param perPage 每页多少个
     */
    private PageBean(Integer curPage, int nearCount, int perPage, long total) {
    	int maxPage = PageBean.getMaxPage(total, perPage);
    	if(curPage == null || curPage < 1){
    		curPage = 1;
    	}
    	if(curPage > maxPage){
    		curPage = maxPage;
    	}
    	this.nearCount=nearCount; 
        this.curPage = curPage;
        this.maxPage = maxPage;
        this.perPage = perPage;
        this.total = total;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
    
    public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
    
    public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	/*
	 * @description 得到最大的页数
	 */
	public static int getMaxPage(long total, int perPage) {
        float tmpF = (float) total / perPage;
        int tmpI = (int) tmpF;
        if (tmpF > tmpI) {
            return tmpI + 1;
        }
        return tmpI;
    }
	
	public static int formart(Integer page){
		if(page == null || page < 1){
			page = 1;
		}
		return page;
	}
}
