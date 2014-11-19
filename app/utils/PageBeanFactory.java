package utils;

/**
 * @author kingda
 * 
 */
public class PageBeanFactory {
	
	private int perpage;
	private int curpage;
	public static int total;//total由dao处理
	public static int maxPage;
	/**
	 * 
	 * @param 当前页数
	 * @param 每页的条数
	 * @return PageBeanFactory
	 */
	
	public PageBeanFactory(int curPage,int perpage){
		this.curpage=curPage;
		this.perpage=perpage;
	}
	/**
	 * @description 得到开始的位置。
	 */
	public int getStartPos(){
		if(curpage==0){
			curpage=1;
		}
		return perpage*(curpage-1);
	}
	
	public void setTotal(int total){
		this.total=total;
	}
	/**@param total 总条数
	 * @param perpage 每页数
	 * @description 得到最大的页数
	 */
	public  int  getMaxPage() {
		if(total==0){
			return 0;
		}
        float tmpF = (float) total / perpage;
        int tmpI = (int) tmpF;
        if (tmpF > tmpI) {
            this.maxPage= tmpI + 1;
            
        }
        else{
        	this.maxPage= tmpI;
        }
        return this.maxPage;
    }
	/**
	 * @description 得到每页的条数
	 */
	public int getPerPage(){
		return this.perpage;
	}
	
	
}
