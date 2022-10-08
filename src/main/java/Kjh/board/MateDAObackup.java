/*
package Kjh.board;

import java.sql.*;
import java.util.*;//ArrayList,HashTable,,,등을 쓰기위해

public class MateDAObackup {

	private DBConnectionMgr pool = null;// 1.객체생성할 멤버변수선언
	// 공통
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;// select를 해서 찾은값을 담는 상자라고 생각하기
	private String sql = "";// 실행시킬 SQL구문

	// 2.생성자를 통해서 연결->의존관계
	public MateDAObackup() {
		try {
			pool = DBConnectionMgr.getInstance();
			System.out.println("pool=>" + pool);
		} catch (Exception e) {
			System.out.println("DB접속 오류=>" + e);
		}
	}

	// 3.메서드 작성(페이징 처리를 위한 메서드)=>총레코드수(=총게시물수=총회원수)
	// select count(*) from board; -> select count(*) from member;
	public int getArticleCount() {
		int x = 0;// 총레코드수
		try {
			con = pool.getConnection();
			System.out.println("con=>" + con);
			sql = "select count(*) from friend";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getArticleCount() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);// 메모리 해제(안하면 메모리가 쌓이기떄문에 하는게좋음)
		}
		return x;
	}

	
		//////////검색어에 해당되는 총레코드수를 구하는 메서드(검색분야,검색어)
		public int getArticleSearchCount(String search,String searchtext) {
			int x=0;//총레코드수
			try {
				con=pool.getConnection();
				System.out.println("con=>"+con);
				
				//-------------------------------------------
				if(search==null || search=="") {//검색분야 선택X
				sql="select count(*) from friend";
				}else {//검색분야(제목,작성자,제목+본문)
					if(search.equals("subject_content")) {//제목+본문
						sql="select count(*) from friend where subject like '%"+
							searchtext+"%' or content like '%"+searchtext+"%' ";
					}else {//제목 or 작성자->매개변수를 이용해서 하나의 sql로 통합
						sql="select count(*) from friend where "+search+" like '%"+
								searchtext+"%' ";
					}
				}
				System.out.println("getArticleSearchCount 검색sql=>"+sql);
				//--------------------------------------------
				 				
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					x=rs.getInt(1);
				}
			}catch(Exception e) {
				System.out.println("getArticleSearchCount() 에러유발=>"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);// 메모리 해제(안하면 메모리가 쌓이기떄문에 하는게좋음)
			}
			return x;
		}
		/////////////////////////////////////////////////////////////////
	 

	// 2.글목록보기에 대한 메서드 구현->레코드 한개이상->한페이지당 10개씩담기
	// 1)레코드의 시작번호 2)불러올 레코드의 갯수(ex 10,20,30,,,,)
	public List<MateDTO> getArticles() {
		List<MateDTO> articleList = null;

		try {
			con = pool.getConnection();
			/*
			 * 그룹번호가 가장 최신의 글을 중심으로 정렬하되,만약에 level이 같은경우 step값으로 오름차순을 통해서 몇번째 레코드번호를 기준해서
			 * 몇개까지 정렬할것인가를 지정해주는 SQL구문
			 */
			sql = "select * from friend order by mate_no";
			pstmt = con.prepareStatement(sql);
			//pstmt.setInt(1, start - 1);// mysql은 레코드순번이 내부적으로 0시작
			//pstmt.setInt(2, end);// 불러와서 담을 갯수(ex 10)
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
				articleList = new ArrayList();
				do {// do while구문은 누적이라서 사용
					MateDTO article = makeArticleFromResult(); // 밑에 만든 메서드로 불러오기
					/*
					 * article.setNum(rs.getInt("num")); article.setId_no(rs.getInt("id_no"));
					 * article.setWriter(rs.getString("writer"));
					 * article.setTitle(rs.getString("title"));
					 * article.setGender(rs.getString("gender"));
					 * article.setCreate_datetime(rs.getTimestamp("create_datetime"));//작성날짜
					 * article.setLifestyle(rs.getString("lifestyle"));
					 * article.setView(rs.getInt("view"));
					 */

					// 추가
					articleList.add(article);// 생략하면 데이터가 저장X-> for문시 에러유발
				} while (rs.next());
			}

		} catch (Exception e) {
			System.out.println("getArticle에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	//(검색어에 따른 레코드의 범위지정에 대한 메서드)
		public List<MateDTO>getFriendArticles(int start,int end,String search,String searchtext){
			List<MateDTO> articleList=null;
			
			try {
				con=pool.getConnection();
				//---------------------------------------------------------------------
				if(search==null || search=="") {
				sql="select * from friend order by ref desc,re_step limit ?,?";
				}else {//제목+본문
					if(search.equals("subject_content")) {//제목+본문
						sql="select * from friend where subject like '%"+
							searchtext+"%' or content like '%"+searchtext+"%' order by ref desc,re_step limit ?,?";
						}else {//제목 or 작성자->매개변수를 이용해서 하나의 sql로 통합
							sql="select * from friend where "+search+" like '%"+
									searchtext+"%' order by ref desc,re_step limit ?,?";
						}
				}
				System.out.println("getFriendArticles()의 sql=>"+sql);
				//---------------------------------------------------------------------------
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, start-1);//mysql은 레코드순번이 내부적으로 0시작
				pstmt.setInt(2, end);//불러와서 담을 갯수(ex 10)
				rs=pstmt.executeQuery();
				if(rs.next()) {//보여주는 결과가 있다면
					articleList=new ArrayList(end);//10->end갯수만큼 데이터 담을 공간생성
					do {// do while구문은 누적이라서 사용
						MateDTO article=makeArticleFromResult(); // 밑에 만든 메서드로 불러오기
			
						//추가 
						articleList.add(article);//생략하면 데이터가 저장X-> for문시 에러유발
					}while(rs.next());
				}
				
			}catch(Exception e) {
				System.out.println("getFriendArticle에러유발=>"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return articleList;
		}
	// -----------------------------------------------------------------
	
		//-----------------------------------------------------------------
		//(3)페이징 처리 계산 정리해주는 메서드(ListAction)
		public Hashtable pageList(String pageNum,int count) {
			//1. 페이징 처리결과를 저장할 Hashtable객체 선언

					Hashtable<String,Integer>pgList=new Hashtable<String,Integer>();
			
					int pageSize=5;//=numPerPage=>페이지당 보여주는 게시물수 
					int blockSize=3;//=pagePerBlock=>블럭당 보여주는 페이지수
					
				//게시판을 맨 처음 실행시키면 무조건 1페이지부터 출력->가장 최근의 글부터 출력
				if(pageNum==null){
					pageNum="1";//default(무조건 1페이지는 선택하지 않아도 보여줘야 하기때문에)
				}
				int currentPage=Integer.parseInt(pageNum);//"1"->1(nowPage)->현재 페이지
				//					 (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21  페이지마다 시작 레코드번호
				int startRow=(currentPage-1)*pageSize+1;//시작 레코드번호
				int endRow=currentPage*pageSize;//1*10,2*10=20,3*10=30

				int number=0;//beginPerPage(페이지별로 맨처음에 나오는 게시물번호)
				System.out.println("현재 레코드수(count)=>"+count);

				number=count-(currentPage-1)*pageSize;
				System.out.println("페이지별로 number=>"+number);
				
				//총페이지수,시작,종료페이지 계산->list.jsp에 이미 코딩
				//모델1에서의 list.jsp에서 복사해온다.(모델2는 액션태그로 바꿔놔서안됨)
				//1.총페이지수 구하기 122/10=12.2+1.0=13.2=13(122%10)=1
				int pageCount=count/pageSize+(count%pageSize==0?0:1);//삼항연산자
				//2.시작페이지
				int startPage=0;
				if(currentPage%blockSize!=0){//1~9,11~19,21~29(10의배수X)
					startPage=currentPage/blockSize*blockSize+1;//경계선때문
				}else{//10%10=0(10,20,30,40,,,,)
										//((10/10)-1)*10+1=1  2->11
					startPage=((currentPage/blockSize)-1)*blockSize+1;
				}
				//종료페이지
				int endPage=startPage+blockSize-1;//1+10-1=10,2+10-1=11
				System.out.println("startPage=>"+startPage+",endPage=>"+endPage);
				//블럭별로 구분해서 링크 걸어서 출력()
				if(endPage > pageCount) endPage=pageCount;//마지막=총페이지수
				//페이징 처리에 대한 계산결과=>Hashtable,HashMap에 담아서
				//ListAction에 전달->메모리에 저장,공유->list.jsp로 전달
				 pgList.put("pageSize", pageSize);//<->pgList.get(키명)
				 pgList.put("blockSize", blockSize);
				 pgList.put("currentPage", currentPage);
				 pgList.put("startRow", startRow);
				 pgList.put("endRow", endRow);
				 pgList.put("count", count);
				 pgList.put("number", number);
				 pgList.put("startPage", startPage);
				 pgList.put("endPage", endPage);
				 pgList.put("pageCount", pageCount);
				 
				 return pgList;//ListAction애개 리턴해준다
			}

	
		//게시판에 글쓰기 
		public void insertArticle(MateDTO article, TendencyDTO ten ) {//~(MemberDTO mem)
			// Mate
			int num=article.getMate_no();//0 신규글<->0이 아닌경우(양수) 기존에 있던글
			System.out.println(num);
			
			int number=0;//데이터를 저장하기위한 게시물번호(=New)
			System.out.println("insertArticle 메서드의 내부 num=>"+num);
		
			try {
				con=pool.getConnection();
				sql="select max(num) from friend";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {//데이터가 있다면
					number=rs.getInt(1)+1;//최대값+1
				}else {
					number=1;//테이블에 한개의 데이터가 없다면 최초 1부여
				}
			
				//작성자 성별 제목 성향 라이프스타일 기타사항, 조회수
				sql="insert into friend(mate_no,id_no,writer,gender,title,lifestyle,other_matter,views)";
				sql+="values(?,?,?,?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, article.getMate_no());
				pstmt.setInt(2, article.getId_no());
				pstmt.setString(3, article.getWriter());
				pstmt.setString(4, article.getGender());
				pstmt.setString(5, article.getTitle());
				pstmt.setString(6, article.getLifestyle());
				pstmt.setString(7, article.getOther_matter());
				pstmt.setInt(8, article.getViews());
				
				int insert=pstmt.executeUpdate();
				System.out.println("게시판의 글쓰기성공유무(insert)=>"+insert);//1 or 0
				
			}catch(Exception e) {
				System.out.println("insertArticle()메서드 에러유발=>"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
		}
	
	
	
	/*
	// 글상세보기->소스코드를 적게 사용하는 방법을 선택(=여기서는 조회수먼저 select 나중)

	// update board set readcount=readcount+1 where num=3;
	// select * from board where num=3; 업데이트(조회수)를 먼저하고 select를 하는게 좋다
	// public MemberDTO grtMember(String id){~} <= (회원관리라면)
	public MateDTO getArticle(int num) {
		MateDTO article = null;

		try {
			con = pool.getConnection();
			sql = "update board set readcount=readcount+1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int update = pstmt.executeUpdate();
			System.out.println("조회수 증가유무(update)=>" + update);

			sql = "select * from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();// 실행

			if (rs.next()) {// 레코드가 최소 만족 1개이상 존재한다면
				article = makeArticleFromResult(); // 밑에 만든매소드로 호출
			}
		} catch (Exception e) {
			System.out.println("getArticle에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;// conent.jsp에서 박아서 출력->NullPointException 조심
	}
*/
	
		
	
	// 자주쓰고 반복적인것을 매소드로 뽑음
	// 접근지정자가 private가 되는 경우=>외부에서 호출X 내부클래스사용O
	private MateDTO makeArticleFromResult() throws Exception {
		MateDTO article = new MateDTO();
		article.setMate_no(rs.getInt("mate_no"));
		article.setId_no(rs.getInt("id_no"));
		article.setWriter(rs.getString("writer"));
		article.setTitle(rs.getString("title"));
		article.setGender(rs.getString("gender"));
		article.setCreated_datetime(rs.getTimestamp("create_datetime"));// 작성날짜
		article.setLifestyle(rs.getString("lifestyle"));
		article.setViews(rs.getInt("views"));
		return article;
	}
/*
	// select * from board where num=3;
	// 글수정
	// 1)수정할 데이터를 찾을 메서드 필요
	public MateDTO updateGetArticle(int num) {
		MateDTO article = null;
		try {
			con = pool.getConnection();
			sql = "select * from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 레코드가 최소 만족 1개이상 존재한다면
				article = makeArticleFromResult();
			}
		} catch (Exception e) {
			System.out.println("updateGetArticle에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;// updateForm.jsp에서 반환받기
	}

	// 2)수정시켜주는 메서드 작성->본인확인->회원탈퇴처럼(암호를 비교=>탈퇴)
	public int updateArticle(MateDTO article) {
		String dbpasswd = "";// DB상에서 찾은 암호를 저장
		int x = -1;// 게시물의 수정유무

		try {
			con = pool.getConnection();
			sql = "select passwd from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();

			if (rs.next()) {// 데이터가 있다면
				dbpasswd = rs.getString("passwd");
				System.out.println("dbpasswd=>" + dbpasswd);

				if (dbpasswd.equals(article.getPasswd())) {

					sql = "update board set writer=?,email=?,subject=?,";
					sql += " passwd=?, content=? where num=? ";

					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getEmail());
					pstmt.setString(3, article.getSubject());
					pstmt.setString(4, article.getPasswd());
					pstmt.setString(5, article.getContent());
					pstmt.setInt(6, article.getNum());

					int update = pstmt.executeUpdate();
					System.out.println("게시판의 글수정 성공유무(update)=>" + update);// 1 or 0
					x = 1;// 글수정성공
				} else {
					x = 0;
				}
			}
		} catch (Exception e) {
			System.out.println("updateArticle()메서드 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

	// 삭제시켜주는 메서드
	// select passwd from board where num=?// hidden을 사용 ->직접넘겨받지 않는다=>get방식을 사용하지
	// 않는다
	// delete from board where num=?
	public int deleteArticle(int num, String passwd) {
		String dbpasswd = "";// DB상에서 찾은 암호를 저장
		int x = -1;// 게시물의 수정유무

		try {
			con = pool.getConnection();
			sql = "select passwd from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {// 데이터가 있다면
				dbpasswd = rs.getString("passwd");
				System.out.println("dbpasswd=>" + dbpasswd);
				/*
				 * 암호가 틀리다고 하는경우->num가 제대로 전달되는지 확인 입력받은 암호를 제대로 검증을 못하는 경우 ex)passwd를 "passwd"로
				 * 잘못입력
				 */
	
	/*
				if (dbpasswd.equals(passwd)) {

					sql = "delete from board where num=? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					int delete = pstmt.executeUpdate();
					System.out.println("게시판의 글삭제 성공유무(delete)=>" + delete);// 1 or 0
					x = 1;// 글삭제성공
				} else {
					x = 0;// 삭제실패
				}
			}
		} catch (Exception e) {
			System.out.println("deleteArticle()메서드 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	*/
}
*/