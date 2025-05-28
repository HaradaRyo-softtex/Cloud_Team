package jp.co.sss.shop.controller.client.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.shop.bean.UserBean;
import jp.co.sss.shop.constant.Constant;
import jp.co.sss.shop.constant.URLConstant;
import jp.co.sss.shop.dao.OrderDao;
import jp.co.sss.shop.dto.OrderDetail;
import jp.co.sss.shop.dto.OrderItem;

/**
 * Servlet implementation class OrderShowDetailController
 */
@WebServlet("/order/detail")
public class OrderShowDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<OrderDetail> orderDetailsList = new ArrayList<>();
        List<OrderItem> orderItemList = new ArrayList<>();
        
        try {
            HttpSession session = request.getSession();
            UserBean user_bean =  (UserBean) session.getAttribute("user");
            int user_id = user_bean.getId();

            // リクエストパラメータからorderIdを取得（なければ0）
            String orderIdParam = request.getParameter("orderId");
            int orderId = 0;
            if (orderIdParam != null && !orderIdParam.isEmpty()) {
                try {
                    orderId = Integer.parseInt(orderIdParam);
                } catch (NumberFormatException e) {
                    orderId = 0;  // 無効な値の場合は0扱い
                }
            }

            // orderIdが指定されていなければ最新の注文IDを取得
            if (orderId == 0) {
                orderId = OrderDao.getOrderId(user_id);
            }

            if (orderId == 0) {
                // 注文なし
                request.setAttribute("orderDetailsList", orderDetailsList);
                request.setAttribute("orderItemList", orderItemList);
                request.getRequestDispatcher("/jsp/client/order/detail.jsp").forward(request, response);
                return;
            }

            // 注文IDに基づいて詳細と商品情報取得
            orderDetailsList = OrderDao.findOrderDetails(orderId);
            orderItemList = OrderDao.findOrderItem(orderId);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + URLConstant.URL_ERROR_TYPE + Constant.ERROR_CODE_DB);
            return;
        }
        request.setAttribute("orderDetailsList", orderDetailsList);
        request.setAttribute("orderItemList", orderItemList);
        request.getRequestDispatcher("/jsp/client/order/detail.jsp").forward(request, response);
    }
}