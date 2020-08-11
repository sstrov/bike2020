package fscms.mods.code.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import fscms.mods.code.vo.CodeDetailVO;
import fscms.mods.code.vo.CodeSearchVO;
import fscms.mods.code.vo.CodeVO;
import fscms.mods.data.util.DataChghstHelper;
import net.sf.json.JSONArray;

@Service("codeService")
public class CodeServiceImpl extends EgovAbstractServiceImpl implements CodeService {
	
	@Resource(name = "codeMapper")
	private CodeMapper codeDAO;
	
	@Resource(name = "codeDetailService")
	private CodeDetailService codeDetailService;
	
	@Resource(name = "dataChghstHelper")
	private DataChghstHelper dataChghstHelper;

	@Override
	public CodeVO selectObj(CodeVO vo) throws Exception {
		return codeDAO.selectObj(vo);
	}
	
	@Override
	public List<CodeVO> selectList(CodeVO vo) throws Exception {
		return codeDAO.selectList(vo);
	}

	@Override
	public int selectTCount(CodeSearchVO searchVO) throws Exception {
		return codeDAO.selectTCount(searchVO);
	}

	@Override
	public List<CodeVO> selectPageList(CodeSearchVO searchVO) throws Exception {
		return codeDAO.selectPageList(searchVO);
	}

	@Override
	public void insertInfo(HttpServletRequest request, CodeVO vo, String mngrId) throws Exception {
		codeDAO.insertInfo(vo);
		
		// 상세정보 저장
		this.setCodeDti(request, vo.getCodeId(), mngrId);
		
		dataChghstHelper.setInsert(request, "FS_CODE", null, this.getData(vo.getCodeId()), null, mngrId);
	}

	@Override
	public void updateInfo(HttpServletRequest request, String bfData, CodeVO vo, String mngrId, boolean detailAt) throws Exception {
		codeDAO.updateInfo(vo);
		
		if(detailAt) {
			// 상세정보 저장
			this.setCodeDti(request, vo.getCodeId(), mngrId);
		}
		
		if(StringUtils.isNotEmpty(bfData)) {
			dataChghstHelper.setInsert(request, "FS_CODE", bfData, this.getData(vo.getCodeId()), null, mngrId);
		}
	}
	
	private String getData(String key) throws Exception {
		String rtnData = null;
		
		CodeVO codeVO = new CodeVO();
		codeVO.setCodeId(key);
		CodeVO item = this.selectObj(codeVO);
		
		if(item != null) {
			item.setUpdtDe(null);
			rtnData = JSONArray.fromObject(item).toString();
		}
		return rtnData;
	}
	
	// 상세정보 저장
	private void setCodeDti(HttpServletRequest request, String codeId, String mngrId) throws Exception {
		
		String[] detailSnArr = request.getParameterValues("codeDetailSn");
		String[] detailIdArr = request.getParameterValues("codeDetailId");
		String[] detailNmArr = request.getParameterValues("codeDetailNm");
		String[] detailDcArr = request.getParameterValues("codeDetailDc");
		
		// 상세코드 정보 삭제
		String keyArrString = "";
		if(detailSnArr != null && detailSnArr.length > 0) {
			for (String str : detailSnArr) {
				if(StringUtils.isNotEmpty(str)) {
					keyArrString += ((StringUtils.isNotEmpty(keyArrString))? "," : "") + str;
				}
			}
		}
		
		CodeDetailVO codeDetailVO = new CodeDetailVO();
		codeDetailVO.setCodeId(codeId);
		codeDetailVO.setDeleteAt("N");
		if(StringUtils.isNotEmpty(keyArrString)) {
			codeDetailVO.setKeyArr(keyArrString.split(","));
		}
		List<CodeDetailVO> itemList = codeDetailService.selectList(codeDetailVO);
		
		if(itemList != null && itemList.size() > 0) {
			for (CodeDetailVO item : itemList) {
				// 데이터 삭제 (실데이터는 삭제하지 않음)
				codeDetailVO = new CodeDetailVO();
				codeDetailVO.setCodeDetailSn(item.getCodeDetailSn());
				codeDetailVO.setDeleteAt("Y");
				
				item.setUpdtDe(null);
				String bfData = JSONArray.fromObject(item).toString();
				codeDetailService.updateInfo(request, bfData, codeDetailVO, mngrId);
			}
		}
		
		// 데이터 저장
		if(detailIdArr != null && detailIdArr.length > 0) {
			for(int i=0; i<detailIdArr.length; i++) {
				if(StringUtils.isEmpty(detailIdArr[i]) || StringUtils.isEmpty(detailNmArr[i])) {
					continue;
				}
				
				codeDetailVO = new CodeDetailVO();
				codeDetailVO.setCodeId(codeId);
				codeDetailVO.setCodeDetailId(detailIdArr[i]);
				codeDetailVO.setCodeDetailNm(detailNmArr[i]);
				codeDetailVO.setCodeDetailDc(detailDcArr[i]);
				codeDetailVO.setCodeDetailOrdr(i + 1);
				codeDetailVO.setDeleteAt("N");
				
				if(StringUtils.isNotEmpty(detailSnArr[i])) {
					// 키 정보가 있으면 수정
					CodeDetailVO vo = new CodeDetailVO();
					vo.setCodeDetailSn(Integer.parseInt(detailSnArr[i]));
					CodeDetailVO item = codeDetailService.selectObj(vo);
					
					if(item != null) {
						codeDetailVO.setCodeDetailSn(Integer.parseInt(detailSnArr[i]));
						
						item.setUpdtDe(null);
						String bfData = JSONArray.fromObject(item).toString();
						codeDetailService.updateInfo(request, bfData, codeDetailVO, mngrId);
					}
				} else {
					codeDetailService.insertInfo(request, codeDetailVO, mngrId);
				}
			}
		}
	}

}
