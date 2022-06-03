/**
 *
 * @author Rushi
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArithmeticCalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String valOne = request.getParameter("first_val");
        String valTwo = request.getParameter("second_val");
        String act = request.getParameter("button");
        String result;
        Integer resultNum = null;
        boolean numberCheck = isNumeric(valOne, valTwo);
        int valOneNum = stringToInt(valOne);
        int valTwoNum = stringToInt(valTwo);

        switch (act) {
            case "+":   
                resultNum = valOneNum + valTwoNum;
                break;
            case "-":
                resultNum = valOneNum - valTwoNum;
                break;
            case "*":
                resultNum = valOneNum * valTwoNum;
                break;
            case "%":                    
                resultNum = valOneNum / valTwoNum;
                break;
            default:
                break;
        }
        // Enters if any kind of math was done
        if(resultNum != null && numberCheck == true){
            result = Integer.toString(resultNum);
        }
        // Enters if user did not enter a number
        else if(numberCheck == false) {
            result = "Invalid";
        }
        else {
            result = "---";
        }
        request.setAttribute("result", result);
        request.setAttribute("valOne", valOne);
        request.setAttribute("valTwo", valTwo);
        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp").forward(request, response);
        return;
    }
    public boolean isNumeric(String valOne, String valTwo) {
        if(valOne == null || valTwo == null) {
            return false;
        }
        try {
            int valOneNum = Integer.parseInt(valOne);
            int valTwoNum = Integer.parseInt(valTwo);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public int stringToInt(String val) {
        int valNum;
        try {
            valNum = Integer.parseInt(val);
        }
        catch (NumberFormatException nfe) {
            return 0;
        }
        return valNum;
    }
}