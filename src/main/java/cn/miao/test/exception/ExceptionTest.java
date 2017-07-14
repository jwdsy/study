package cn.miao.test.exception;

import cn.miao.exception.AppRuntimeException;
import cn.miao.exception.ServiceException;
import cn.miao.resp.InfoCode;

public class ExceptionTest {

	public static void main(String[] args) throws ServiceException {
		int i = 2;
		try {
			if (i == 0) {
				throw new Exception("Exception");
			} else if (i == 1) {
				throw new ServiceException("1", "ServiceException");
			} else if (i == 2) {
				throw new AppRuntimeException(InfoCode.CODE_NOT_FOUND);
			} else if (i == 3) {
				throw new Error("Error");
			}
		} catch (AppRuntimeException e) {
			System.err.println("catch (AppRuntimeException e)"+e);
			throw new AppRuntimeException(InfoCode.CODE_NOT_FOUND);
		} catch (ServiceException e) {
			System.err.println("catch (ServiceException e)"+e);
			throw new ServiceException("2", e.getMessage());
		} catch (Exception e) {
			System.err.println("catch (Exception e)"+e);
		}
	}

}
