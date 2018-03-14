using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web.Mvc;

namespace PC.Framework
{
    public static class CSRFHelper
    {

        #region Variables
        private static IList<string> _csrfTokenList = new List<string>();
        private static List<Exception> _errorList = new List<Exception>();

        public static bool HasError
        {
            get
            {
                return _errorList.Count > 0;
            }
        }

        #endregion

        #region Methods

        /// <summary>
        /// Generate and store new CSRF Token
        /// </summary>
        /// <returns>Encoded Token string</returns>
        public static string Generate()
        {
            try
            {
                Guid guid = Guid.NewGuid();
                string token = Security.Base64Encode(guid.ToString());
                _csrfTokenList.Add(token);
                return token;
            }
            catch (Exception ex)
            {
                AddToExceptionList(ex);
                return "ERROR";
            }
        }
        public static string Generate(Controller controller, string sessionName = "CSRFToken")
        {
            try
            {
                string token = Generate();
                controller.Session[sessionName] = token;
                if (controller.Session[sessionName] != null &&
                    !string.IsNullOrEmpty(controller.Session[sessionName].ToString()) &&
                    !string.IsNullOrWhiteSpace(controller.Session[sessionName].ToString()))
                    return token;
                return "";
            }
            catch (Exception ex)
            {
                AddToExceptionList(ex);
                return "ERROR";
            }
        }
        /// <summary>
        /// Check CSRF Token is Valid
        /// </summary>
        /// <param name="token">csrf token</param>
        /// <returns>isvalid</returns>
        public static bool IsValid(string token)
        {
            try
            {
                if (_csrfTokenList.Contains(token))
                {
                    _csrfTokenList.Remove(token);
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch (Exception ex)
            {
                AddToExceptionList(ex);
                return false;
            }
        }
        public static bool IsValid(Controller controller, string token, string sessionName = "CSRFToken")
        {
            try
            {
                if (controller.Session[sessionName] == null ||
                    string.IsNullOrEmpty(controller.Session[sessionName].ToString()) ||
                    string.IsNullOrWhiteSpace(controller.Session[sessionName].ToString()))
                    return false;

                if (IsValid(token))
                    return true;

                return false;
            }
            catch (Exception ex)
            {
                AddToExceptionList(ex);
                return false;
            }
        }


        #endregion

        #region Helper Methods

        private static void AddToExceptionList(Exception ex)
        {
            try
            {
                if (_errorList == null || _errorList.Count == 0)
                    _errorList = new List<Exception>();
                _errorList.Add(ex);
            }
            catch (Exception)
            {
            }
        }

        public static List<Exception> GetAllExceptions()
        {
            try
            {
                return _errorList;
            }
            catch (Exception)
            {
                return new List<Exception>();
            }
        }

        #endregion

    }
}
