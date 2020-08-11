package fscms.cmm.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;

@Component
public class ThumbHelper {
	
	/**
	 * @throws IOException 
	 * @Method Name	: createImageMng
	 * @작성일   		: 2019. 3. 8.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 썸네일 이미지 생성
	 */
	public void createImageMng(String loadDir, String loadFile, String fileExt, int w, int h, String thumbNm) throws IOException {
		if(isImageFile(fileExt)) {
			File f = new File(loadDir + "/" + loadFile);
			
			if(f.isFile()) {
				BufferedImage bi = ImageIO.read(f);
				
				// 원본 이미지의 너비와 높이 입니다.
				int ow = 0;
				int oh = 0;
				
				if(bi != null) {
					ow = bi.getWidth();
					oh = bi.getHeight();
				}
				
				/*
				// 늘어날 길이를 계산하여 패딩합니다.
				int pd = 0;
				if(ow > oh) {
					pd = (int)(Math.abs((h * ow / (double)w) - oh) / 2d);
				} else {
					pd = (int)(Math.abs((w * oh / (double)h) - ow) / 2d);
				}
				bi = Scalr.pad(bi, pd, Color.WHITE, Scalr.OP_ANTIALIAS);
				
				// 이미지 크기가 변경되어 다시 구함
				ow = bi.getWidth();
				oh = bi.getHeight();
				*/
				
				// 원본 너비를 기준으로 하여 썸네일의 비율로 높이를 계산합니다.
				int nw = ow;
				int nh = (ow * h) / w;
				
				// 계산된 높이가 원본보다 높다면 crop이 안되므로
				// 원본 높이를 기준으로 썸네일의 비율로 너비를 계산합니다.
				if(nh > oh) {
					nw = (oh * w) / h;
					nh = oh;
				}
				
				// 계산된 크기로 원본이미지를 가운데에서 crop 합니다.
				BufferedImage cropImg = Scalr.crop(bi, (ow-nw)/2, (oh-nh)/2, nw, nh);
				
				// crop된 이미지로 썸네일을 생성합니다.
				BufferedImage destImg = Scalr.resize(cropImg, w, h);
				
				// 썸네일을 저장합니다. 이미지 이름 앞에 "WxH" 를 붙여 표시했습니다.
				String saveFile = w + "x" + h + "_" + loadFile;
				if(StringUtils.isNotEmpty(thumbNm)) {
					// 썸네일 생성명이 있으면 생성명으로 생성
					saveFile = thumbNm + "_" + loadFile;
				}
				File thumbFile = new File(loadDir + "/" + saveFile);
				
				ImageIO.write(destImg, fileExt.toUpperCase(), thumbFile);
			}
		}
	}
	
	/**
	 * @Method Name	: isImageFile
	 * @작성일   		: 2019. 3. 8.
	 * @작성자   		: Edmund.J
	 * @변경이력  		:
	 * @Method 설명 	: 이미지 체크
	 */
	public boolean isImageFile(String fileExt) {
		String[] imgList = { "gif", "jpg", "jpeg", "png", "tiff", "bmp" };
		Arrays.sort(imgList);
		
		if(Arrays.binarySearch(imgList, fileExt.toLowerCase()) < 0) {
			return false;
		}
		return true;
	}

}
