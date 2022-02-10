package com.oms.projectbuddy.utility;

import jakarta.inject.Singleton;

import java.time.LocalDate;

@Singleton
public class EmailTemplates {

    public String projectCreationTemplate(String projectName,String projectNumber,String contactName,String contactEmail,String contactNumber){
        String message="<style>\n" +
                "  @import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;700&display=swap');\n" +
                "  .bg-image{\n" +
                "      background-image: url(images/Background-Image.png);background-repeat: no-repeat;\n" +
                "    }\n" +
                "</style>\n" +
                "<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\n" +
                "  <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
                "    <table width=\"600\" height=\"108\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"bg-image border-lr deviceWidth\" bgcolor=\"#F4F4F4\">\n" +
                "      <tbody>\n" +
                "        <tr>\n" +
                "          <td align=\"center\" style=\"padding-top: 35px;padding-bottom: 35px;\">\n" +
                "            <div class=\"logo\"><img src=\"images/sourceably-logo.png\" alt=\"\" style=\"width:50%\"></div>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "        <td style=\"padding-right: 50px;padding-left: 50px;padding-bottom: 30px;\">\n" +
                "          <table width=\"100%\" style=\"background: #fff;border-radius: 2px;\">\n" +
                "            <tbody>\n" +
                "              <tr>\n" +
                "              <td align=\"center\" style=\"padding: 25px;\">\n" +
                "                 <h2 style=\"font: normal normal bold 26px/41px Arial;letter-spacing: 0px;color: #3A72B9;text-transform: uppercase;\">Congratulations!</h2>\n" +
                "                 <h3 style=\"font: normal normal bold 22px/35px Arial;color: #042F57;text-transform: capitalize;\">You Have Created A New Project!!</h3>\n" +
                "                 <p style=\"font: normal normal normal 19px/26px Arial;color: #042F57;text-transform: capitalize;\">We're excited to have you get started. first, you need to confirm your project details.</p>\n" +
                "                 <p style=\"box-shadow: 0px 3px 6px #00000029;border-bottom: 1px solid #3A72B9;width: 100px;\"></p>\n" +
                "                </td>   \n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                 <h3 style=\"text-align:center; font: normal normal bold 28px/28px Arial;color: #ED7D31;text-transform: capitalize;\">Here's What You Created :</h3>\n" +
                "                   <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Project Name: <span style=\"font: normal normal bold 17px/25px Arial;\">"+projectName+"</span></p>\n" +
                "                   <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Project Number: <span style=\"font: normal normal bold 17px/25px Arial;\">"+projectNumber+"</span></p>\n" +
                "                   <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Contact Number : <span style=\"font: normal normal bold 17px/25px Arial;\"> "+contactNumber+" </span></p><br>\n" +
                "                   <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Created By : <span style=\"font: normal normal bold 17px/25px Arial;\">"+contactName+"</span></p>\n" +
                "                   <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Contact Email : <span style=\"font: normal normal bold 17px/25px Arial;\">"+contactEmail+"</span></p>\n" +
                "                   <p style=\"border-bottom: 1px dashed #ED7D31;\"></p>\n" +
                "                </td>    \n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                <p style=\"font: normal normal normal 19px/29px Arial;color: #042F57;text-transform: capitalize;\">To Access And Check Status On Your Project Please Press The Button Below</p>\n" +
                "                <button style=\"background: #ED7D31 0% 0% no-repeat padding-box;box-shadow: 0px 3px 6px #00000029;border-radius: 10px;color: #fff;font-weight: bold;font-size: 16px;border: none;padding: 12px 25px;\">Click Here</button>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                <p style=\"font: normal normal normal 20px/32px Arial;color: #042F57;text-transform: uppercase;\">THANK YOU FOR USING <a href=\"\" style=\"font: normal normal bold 20px/32px Arial;text-decoration: none;color: #042F57;\">SOURCEABLY.COM</a></p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody></table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "    <table width=\"600\" height=\"85\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"border-lr deviceWidth\" style=\"background: #f4f4f4 0% 0% no-repeat padding-box;\">\n" +
                "      <tbody>\n" +
                "        <tr>\n" +
                "        <td align=\"center\" style=\"padding-right: 50px;padding-left: 50px;\">\n" +
                "          <table width=\"100%\" style=\"background: #CBD8E8 0% 0% no-repeat padding-box;border-radius: 2px;\">\n" +
                "            <tbody>\n" +
                "              <tr>\n" +
                "              <td align=\"center\" style=\"padding-top: 30px;padding-bottom: 30px;\">\n" +
                "                <h3 style=\"font: normal normal bold 18px/32px Arial;color: #042F57;text-transform: capitalize;\">Need More Help?</h3>\n" +
                "                <p><a href=\"\" style=\"text-decoration: underline;font: normal normal bold 19px/32px Arial;color: #3A72B9;text-transform: capitalize;\">We Are Ready To Help</a></p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "    <table  width=\"600\" height=\"108\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"border-lr deviceWidth\" bgcolor=\"#F4F4F4\">\n" +
                "      <tr>\n" +
                "        <td style=\"padding: 30px 50px;\">\n" +
                "          <h3 style=\"font: normal normal bold 16px/32px Arial;color: #042F57\">Sourceably Confidentiality Notice:</h3>\n" +
                "          <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">This electronic mail transmission is intended for the useof the individual or entity to which it is addressed and may contain confidential or proprietary information belonging to the sender.</p>\n" +
                "          <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">If you are not the intended recipient, you are hereby notified that any disclosure, copying, distribution, or the taking of any action in reliance on the contents of this information is strictly prohibited.</p>\n" +
                "          <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">If you have received this transmission in error, please notify the sender immediately by e-mail support@sourceably.com and delete the original message. Thank you for your cooperation.</p>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </table>\n" +
                "</body>";
        return message;

    }
    public String emailMarketPlace(String projectName, String projectNumber, LocalDate projectCreationDate, LocalDate projectPosted, LocalDate expireDate, String owner, String contactEmail){
        String message="<style>\n" +
                "    @import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;700&display=swap');\n" +
                "    .bg-image{\n" +
                "      background-image: url(images/Background-Image.png);background-repeat: no-repeat;\n" +
                "    }\n" +
                "  </style>\n" +
                "  <body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\n" +
                "    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
                "      <table width=\"600\" height=\"108\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"bg-image border-lr deviceWidth\" bgcolor=\"#F4F4F4\">\n" +
                "        <tbody>\n" +
                "            <tr>\n" +
                "                <td align=\"center\" style=\"padding-top: 35px;padding-bottom: 35px;\">\n" +
                "                  <p><img src=\"images/sourceably-logo.png\" alt=\"\" style=\"width:50%\"></p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "          <tr>\n" +
                "          <td style=\"padding-right: 50px;padding-left: 50px;padding-bottom: 30px;\">\n" +
                "            <table width=\"100%\" style=\"background: #fff;border-radius: 2px;\">\n" +
                "              <tbody><tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;\">\n" +
                "                   <h2 style=\"font: normal normal bold 26px/41px Arial;letter-spacing: 0px;color: #3A72B9;text-transform: uppercase;\">Congratulations!</h2>\n" +
                "                   <h3 style=\"font: normal normal bold 22px/35px Arial;color: #042F57;text-transform: capitalize;\">You Have Successfully Posted A New Project On Market Place!!</h3>\n" +
                "                   <p style=\"font: normal normal normal 19px/26px Arial;color: #042F57;text-transform: capitalize;\">We're excited to have you get started. first, you need to confirm your project details.</p>\n" +
                "                   <p style=\"box-shadow: 0px 3px 6px #00000029;border-bottom: 1px solid #3A72B9;width: 100px;\"></p>\n" +
                "                  </td>   \n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                   <h3 style=\"text-align:center; font: normal normal bold 28px/28px Arial;color: #ED7D31;text-transform: capitalize;\">Here's What You Created :</h3>\n" +
                "                     <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Project Name: <span style=\"font: normal normal bold 17px/25px Arial;\">"+projectName+"</span></p>\n" +
                "                     <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Project Number: <span style=\"font: normal normal bold 17px/25px Arial;\">"+projectNumber+"</span></p>\n" +
                "                     <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Project Creation Date : <span style=\"font: normal normal bold 17px/25px Arial;\">"+projectCreationDate+"</span></p>\n" +
                "                     <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Project Posted On Market Place : <span style=\"font: normal normal bold 17px/25px Arial;\">"+projectPosted+"</span></p>\n" +
                "                     <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Project Set To Expire On Market Place : <span style=\"font: normal normal bold 17px/25px Arial;\">"+expireDate+"</span></p>\n" +
                "                     <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Created By : <span style=\"font: normal normal bold 17px/25px Arial;\">"+owner+"</span></p>\n" +
                "                     <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Contact Email : <span style=\"font: normal normal bold 17px/25px Arial;\">"+contactEmail+"</span></p>\n" +
                "                     <p style=\"border-bottom: 1px dashed #ED7D31;\"></p>\n" +
                "                  </td>    \n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                  <p style=\"font: normal normal normal 19px/29px Arial;color: #042F57;text-transform: capitalize;\">To Access And Check Status On Your Project Please Press The Button Below</p>\n" +
                "                  <button style=\"background: #ED7D31 0% 0% no-repeat padding-box;box-shadow: 0px 3px 6px #00000029;border-radius: 10px;color: #fff;font-weight: bold;font-size: 16px;border: none;padding: 12px 25px;\">Click Here</button>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                  <p style=\"font: normal normal normal 20px/32px Arial;color: #042F57;text-transform: uppercase;\">THANK YOU FOR USING <a href=\"\" style=\"font: normal normal bold 20px/32px Arial;text-decoration: none;color: #042F57;\">SOURCEABLY.COM</a></p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "      <table width=\"600\" height=\"85\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"border-lr deviceWidth\" style=\"background: #f4f4f4 0% 0% no-repeat padding-box;\">\n" +
                "        <tbody><tr>\n" +
                "          <td align=\"center\" style=\"padding-right: 50px;padding-left: 50px;\">\n" +
                "            <table width=\"100%\" style=\"background: #CBD8E8 0% 0% no-repeat padding-box;border-radius: 2px;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                <td align=\"center\" style=\"padding-top: 30px;padding-bottom: 30px;\">\n" +
                "                  <h3 style=\"font: normal normal bold 18px/32px Arial;color: #042F57;text-transform: capitalize;\">Need More Help?</h3>\n" +
                "                  <p><a href=\"\" style=\"text-decoration: underline;font: normal normal bold 19px/32px Arial;color: #3A72B9;text-transform: capitalize;\">We Are Ready To Help</a></p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody>\n" +
                "          </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "      <table  width=\"600\" height=\"108\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"border-lr deviceWidth\" bgcolor=\"#F4F4F4\">\n" +
                "        <tr>\n" +
                "          <td style=\"padding: 30px 50px;\">\n" +
                "            <h3 style=\"font: normal normal bold 16px/32px Arial;color: #042F57\">Sourceably Confidentiality Notice:</h3>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">This electronic mail transmission is intended for the useof the individual or entity to which it is addressed and may contain confidential or proprietary information belonging to the sender.</p>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">If you are not the intended recipient, you are hereby notified that any disclosure, copying, distribution, or the taking of any action in reliance on the contents of this information is strictly prohibited.</p>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">If you have received this transmission in error, please notify the sender immediately by e-mail support@sourceably.com and delete the original message. Thank you for your cooperation.</p>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    </table>\n" +
                "  </body>";
        return message;
    }

    public String welcomeTemplate(String userId,LocalDate dateOfRegistration){
        String message="<style>\n" +
                "    @import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;700&display=swap');\n" +
                "    .bg-image{\n" +
                "        background-image: url(/home/at101pune014/Desktop/Om Software/projectbuddy_lamda/src/main/resources/EmailImages/Background-Image.png);background-repeat: no-repeat;\n" +
                "      }\n" +
                "  </style>\n" +
                "  <body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\n" +
                "    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
                "      <table width=\"600\" height=\"108\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"bg-image border-lr deviceWidth\" bgcolor=\"#F4F4F4\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\" style=\"padding-top: 35px;padding-bottom: 35px;\">\n" +
                "              <div class=\"logo\"><img src=\"images/sourceably-logo.png\" alt=\"\" style=\"width:50%\"></div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "          <td style=\"padding-right: 50px;padding-left: 50px;padding-bottom: 30px;\">\n" +
                "            <table width=\"100%\" style=\"background: #fff;border-radius: 2px;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;\">\n" +
                "                   <h2 style=\"font: normal normal bold 26px/41px Arial;letter-spacing: 0px;color: #3A72B9;text-transform: uppercase;\">Welcome To Sourceably..!</h2>\n" +
                "                  </td>   \n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                     <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">User ID: <span style=\"font: normal normal bold 17px/25px Arial;\">"+userId+"</span></p> <br>\n" +
                "                     <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Date of Registration: <span style=\"font: normal normal bold 17px/25px Arial;\">"+dateOfRegistration+"</span></p>\n" +
                "                     <p style=\"border-bottom: 1px dashed #ED7D31;\"></p>\n" +
                "                  </td>    \n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                  <p style=\"font: normal normal normal 19px/29px Arial;color: #042F57;text-transform: capitalize;\">To Access And Check Status On Your Project Please Press The Button Below</p>\n" +
                "                  <button style=\"background: #ED7D31 0% 0% no-repeat padding-box;box-shadow: 0px 3px 6px #00000029;border-radius: 10px;color: #fff;font-weight: bold;font-size: 16px;border: none;padding: 12px 25px;\">Click Here</button>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                  <p style=\"font: normal normal normal 20px/32px Arial;color: #042F57;text-transform: uppercase;\">THANK YOU FOR USING <a href=\"\" style=\"font: normal normal bold 20px/32px Arial;text-decoration: none;color: #042F57;\">SOURCEABLY.COM</a></p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "      <table width=\"600\" height=\"85\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"border-lr deviceWidth\" style=\"background: #f4f4f4 0% 0% no-repeat padding-box;\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "          <td align=\"center\" style=\"padding-right: 50px;padding-left: 50px;\">\n" +
                "            <table width=\"100%\" style=\"background: #CBD8E8 0% 0% no-repeat padding-box;border-radius: 2px;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                <td align=\"center\" style=\"padding-top: 30px;padding-bottom: 30px;\">\n" +
                "                  <h3 style=\"font: normal normal bold 18px/32px Arial;color: #042F57;text-transform: capitalize;\">Need More Help?</h3>\n" +
                "                  <p><a href=\"\" style=\"text-decoration: underline;font: normal normal bold 19px/32px Arial;color: #3A72B9;text-transform: capitalize;\">We Are Ready To Help</a></p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody>\n" +
                "          </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "      <table  width=\"600\" height=\"108\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"border-lr deviceWidth\" bgcolor=\"#F4F4F4\">\n" +
                "        <tr>\n" +
                "          <td style=\"padding: 30px 50px;\">\n" +
                "            <h3 style=\"font: normal normal bold 16px/32px Arial;color: #042F57\">Sourceably Confidentiality Notice:</h3>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">This electronic mail transmission is intended for the useof the individual or entity to which it is addressed and may contain confidential or proprietary information belonging to the sender.</p>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">If you are not the intended recipient, you are hereby notified that any disclosure, copying, distribution, or the taking of any action in reliance on the contents of this information is strictly prohibited.</p>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">If you have received this transmission in error, please notify the sender immediately by e-mail support@sourceably.com and delete the original message. Thank you for your cooperation.</p>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    </table>\n" +
                "  </body>";
        return message;
    }

    public  String paymentTemplate(String LicensingId,Long startDate,String validity,String userId,String address){
        String message="<style>\n" +
                "    @import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;700&display=swap');\n" +
                "    .bg-image{\n" +
                "        background-image: url(images/Background-Image.png);background-repeat: no-repeat;\n" +
                "      }\n" +
                "  </style>\n" +
                "  <body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\n" +
                "    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
                "      <table width=\"600\" height=\"108\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"bg-image border-lr deviceWidth\" bgcolor=\"#F4F4F4\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\" style=\"padding-top: 35px;padding-bottom: 35px;\">\n" +
                "              <div class=\"logo\"><img src=\"images/sourceably-logo.png\" alt=\"\" style=\"width:50%\"></div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "          <td style=\"padding-right: 50px;padding-left: 50px;padding-bottom: 30px;\">\n" +
                "            <table width=\"100%\" style=\"background: #fff;border-radius: 2px;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;\">\n" +
                "                   <h2 style=\"font: normal normal bold 26px/41px Arial;letter-spacing: 0px;color: #3A72B9;text-transform: uppercase;\">Your Payment Succesfully Credited..!!</h2>\n" +
                "                  </td>   \n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                     <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Licensing ID: <span style=\"font: normal normal bold 17px/25px Arial;\">"+LicensingId+"</span></p> <br>\n" +
                "                     <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Start Date: <span style=\"font: normal normal bold 17px/25px Arial;\">"+startDate+" </span></p><br>\n" +
                "                     <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Validity/Renewal: <span style=\"font: normal normal bold 17px/25px Arial;\">"+validity+" </span></p><br>\n" +
                "                     <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">User ID: <span style=\"font: normal normal bold 17px/25px Arial;\">"+userId+" </span></p>\n" +
                "                     <p style=\"font: normal normal normal 17px/25px Arial;color: #042F57;text-transform: capitalize;margin: 5px 0px;display: inline-block;\">Address: <span style=\"font: normal normal bold 17px/25px Arial;\"> "+address+"</span></p>\n" +
                "                     <p style=\"border-bottom: 1px dashed #ED7D31;\"></p>\n" +
                "                  </td>    \n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                  <p style=\"font: normal normal normal 19px/29px Arial;color: #042F57;text-transform: capitalize;\">To Access And Check Status On Your Project Please Press The Button Below</p>\n" +
                "                  <button style=\"background: #ED7D31 0% 0% no-repeat padding-box;box-shadow: 0px 3px 6px #00000029;border-radius: 10px;color: #fff;font-weight: bold;font-size: 16px;border: none;padding: 12px 25px;\">Click Here</button>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                  <p style=\"font: normal normal normal 20px/32px Arial;color: #042F57;text-transform: uppercase;\">THANK YOU FOR USING <a href=\"\" style=\"font: normal normal bold 20px/32px Arial;text-decoration: none;color: #042F57;\">SOURCEABLY.COM</a></p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "      <table width=\"600\" height=\"85\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"border-lr deviceWidth\" style=\"background: #f4f4f4 0% 0% no-repeat padding-box;\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "          <td align=\"center\" style=\"padding-right: 50px;padding-left: 50px;\">\n" +
                "            <table width=\"100%\" style=\"background: #CBD8E8 0% 0% no-repeat padding-box;border-radius: 2px;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                <td align=\"center\" style=\"padding-top: 30px;padding-bottom: 30px;\">\n" +
                "                  <h3 style=\"font: normal normal bold 18px/32px Arial;color: #042F57;text-transform: capitalize;\">Need More Help?</h3>\n" +
                "                  <p><a href=\"\" style=\"text-decoration: underline;font: normal normal bold 19px/32px Arial;color: #3A72B9;text-transform: capitalize;\">We Are Ready To Help</a></p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody>\n" +
                "          </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "      <table  width=\"600\" height=\"108\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"border-lr deviceWidth\" bgcolor=\"#F4F4F4\">\n" +
                "        <tr>\n" +
                "          <td style=\"padding: 30px 50px;\">\n" +
                "            <h3 style=\"font: normal normal bold 16px/32px Arial;color: #042F57\">Sourceably Confidentiality Notice:</h3>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">This electronic mail transmission is intended for the useof the individual or entity to which it is addressed and may contain confidential or proprietary information belonging to the sender.</p>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">If you are not the intended recipient, you are hereby notified that any disclosure, copying, distribution, or the taking of any action in reliance on the contents of this information is strictly prohibited.</p>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">If you have received this transmission in error, please notify the sender immediately by e-mail support@sourceably.com and delete the original message. Thank you for your cooperation.</p>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    </table>\n" +
                "  </body>";
        return message;
    }
    public String supplierTemplate(String message){
        message="<style>\n" +
                "    @import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;700&display=swap');\n" +
                "    .bg-image{\n" +
                "        background-image: url(images/Background-Image.png);background-repeat: no-repeat;\n" +
                "      }\n" +
                "  </style>\n" +
                "  <body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\n" +
                "    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
                "      <table width=\"600\" height=\"108\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"bg-image border-lr deviceWidth\" bgcolor=\"#F4F4F4\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\" style=\"padding-top: 35px;padding-bottom: 35px;\">\n" +
                "              <div class=\"logo\"><img src=\"images/sourceably-logo.png\" alt=\"\" style=\"width:50%\"></div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "          <td style=\"padding-right: 50px;padding-left: 50px;padding-bottom: 30px;\">\n" +
                "            <table width=\"100%\" style=\"background: #fff;border-radius: 2px;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;\">\n" +
                "                   <h2 style=\"font: normal normal bold 26px/41px Arial;letter-spacing: 0px;color: #3A72B9;text-transform: uppercase;\">Your Supplier Profile Created Successfully...!</h2>\n" +
                "                  </td>   \n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                  <p style=\"font: normal normal normal 19px/29px Arial;color: #042F57;text-transform: capitalize;\">To Access And Check Status On Your Project Please Press The Button Below</p>\n" +
                "                  <button style=\"background: #ED7D31 0% 0% no-repeat padding-box;box-shadow: 0px 3px 6px #00000029;border-radius: 10px;color: #fff;font-weight: bold;font-size: 16px;border: none;padding: 12px 25px;\">Click Here</button>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                  <p style=\"font: normal normal normal 20px/32px Arial;color: #042F57;text-transform: uppercase;\">THANK YOU FOR USING <a href=\"\" style=\"font: normal normal bold 20px/32px Arial;text-decoration: none;color: #042F57;\">SOURCEABLY.COM</a></p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "      <table width=\"600\" height=\"85\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"border-lr deviceWidth\" style=\"background: #f4f4f4 0% 0% no-repeat padding-box;\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "          <td align=\"center\" style=\"padding-right: 50px;padding-left: 50px;\">\n" +
                "            <table width=\"100%\" style=\"background: #CBD8E8 0% 0% no-repeat padding-box;border-radius: 2px;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                <td align=\"center\" style=\"padding-top: 30px;padding-bottom: 30px;\">\n" +
                "                  <h3 style=\"font: normal normal bold 18px/32px Arial;color: #042F57;text-transform: capitalize;\">Need More Help?</h3>\n" +
                "                  <p><a href=\"\" style=\"text-decoration: underline;font: normal normal bold 19px/32px Arial;color: #3A72B9;text-transform: capitalize;\">We Are Ready To Help</a></p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody>\n" +
                "          </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "      <table  width=\"600\" height=\"108\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"border-lr deviceWidth\" bgcolor=\"#F4F4F4\">\n" +
                "        <tr>\n" +
                "          <td style=\"padding: 30px 50px;\">\n" +
                "            <h3 style=\"font: normal normal bold 16px/32px Arial;color: #042F57\">Sourceably Confidentiality Notice:</h3>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">This electronic mail transmission is intended for the useof the individual or entity to which it is addressed and may contain confidential or proprietary information belonging to the sender.</p>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">If you are not the intended recipient, you are hereby notified that any disclosure, copying, distribution, or the taking of any action in reliance on the contents of this information is strictly prohibited.</p>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">If you have received this transmission in error, please notify the sender immediately by e-mail support@sourceably.com and delete the original message. Thank you for your cooperation.</p>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    </table>\n" +
                "  </body>";
        return message;


    }
    public  String updateProjectTemplate(String message){
        message="<style>\n" +
                "    @import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;700&display=swap');\n" +
                "    .bg-image{\n" +
                "        background-image: url(images/Background-Image.png);background-repeat: no-repeat;\n" +
                "      }\n" +
                "  </style>\n" +
                "  <body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\n" +
                "    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
                "      <table width=\"600\" height=\"108\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"bg-image border-lr deviceWidth\" bgcolor=\"#F4F4F4\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td align=\"center\" style=\"padding-top: 35px;padding-bottom: 35px;\">\n" +
                "              <div class=\"logo\"><img src=\"images/sourceably-logo.png\" alt=\"\" style=\"width:50%\"></div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "          <td style=\"padding-right: 50px;padding-left: 50px;padding-bottom: 30px;\">\n" +
                "            <table width=\"100%\" style=\"background: #fff;border-radius: 2px;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;\">\n" +
                "                   <h2 style=\"font: normal normal bold 26px/41px Arial;letter-spacing: 0px;color: #3A72B9;text-transform: uppercase;\">Your Project Details Changed...!</h2>\n" +
                "                  </td>   \n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                  <p style=\"font: normal normal normal 19px/29px Arial;color: #042F57;text-transform: capitalize;\">To Access And Check Status On Your Project Please Press The Button Below</p>\n" +
                "                  <button style=\"background: #ED7D31 0% 0% no-repeat padding-box;box-shadow: 0px 3px 6px #00000029;border-radius: 10px;color: #fff;font-weight: bold;font-size: 16px;border: none;padding: 12px 25px;\">Click Here</button>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=\"center\" style=\"padding: 25px;padding-top: 0px;\">\n" +
                "                  <p style=\"font: normal normal normal 20px/32px Arial;color: #042F57;text-transform: uppercase;\">THANK YOU FOR USING <a href=\"\" style=\"font: normal normal bold 20px/32px Arial;text-decoration: none;color: #042F57;\">SOURCEABLY.COM</a></p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "      <table width=\"600\" height=\"85\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"border-lr deviceWidth\" style=\"background: #f4f4f4 0% 0% no-repeat padding-box;\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "          <td align=\"center\" style=\"padding-right: 50px;padding-left: 50px;\">\n" +
                "            <table width=\"100%\" style=\"background: #CBD8E8 0% 0% no-repeat padding-box;border-radius: 2px;\">\n" +
                "              <tbody>\n" +
                "                <tr>\n" +
                "                <td align=\"center\" style=\"padding-top: 30px;padding-bottom: 30px;\">\n" +
                "                  <h3 style=\"font: normal normal bold 18px/32px Arial;color: #042F57;text-transform: capitalize;\">Need More Help?</h3>\n" +
                "                  <p><a href=\"\" style=\"text-decoration: underline;font: normal normal bold 19px/32px Arial;color: #3A72B9;text-transform: capitalize;\">We Are Ready To Help</a></p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody>\n" +
                "          </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "      <table  width=\"600\" height=\"108\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"border-lr deviceWidth\" bgcolor=\"#F4F4F4\">\n" +
                "        <tr>\n" +
                "          <td style=\"padding: 30px 50px;\">\n" +
                "            <h3 style=\"font: normal normal bold 16px/32px Arial;color: #042F57\">Sourceably Confidentiality Notice:</h3>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">This electronic mail transmission is intended for the useof the individual or entity to which it is addressed and may contain confidential or proprietary information belonging to the sender.</p>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">If you are not the intended recipient, you are hereby notified that any disclosure, copying, distribution, or the taking of any action in reliance on the contents of this information is strictly prohibited.</p>\n" +
                "            <p style=\"font: normal normal normal 15px/23px Arial;color: #042F57;\">If you have received this transmission in error, please notify the sender immediately by e-mail support@sourceably.com and delete the original message. Thank you for your cooperation.</p>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    </table>\n" +
                "  </body>";
        return message;
    }
}
