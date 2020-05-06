package com.hjs.automation.springBootSelenium.apibeans.crm;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/***
 * GsonFormat自动生成
 */
@Data
@Builder
public class CRMComplexSearchBean {

    private List<SearchLabelGroupReqListBean> searchLabelGroupReqList;

    @Data
    @Builder
    public static class SearchLabelGroupReqListBean {
        /**
         * logicalCondition : and
         * searchLabelReqList : [{"labelId":"4","groupId":"15","labelWhereValue":"=","logicalCondition":"and","searchLabelParamsReqList":[{"paramsId":5,"paramsValue":["Male"],"paramsOperator":""}]},{"labelId":"6","labelWhereValue":"in","logicalCondition":"and","searchLabelParamsReqList":[{"paramsId":7,"paramsValue":["Married","Single"],"paramsOperator":""}],"groupId":"15"}]
         */

        private String logicalCondition;
        private List<SearchLabelReqListBean> searchLabelReqList;

//        public void setSearchLabelReqList() {
//        }

        @Data
        @Builder
        public static class SearchLabelReqListBean {
            /**
             * labelId : 4
             * groupId : 15
             * labelWhereValue : =
             * logicalCondition : and
             * searchLabelParamsReqList : [{"paramsId":5,"paramsValue":["Male"],"paramsOperator":""}]
             */

            private String labelId;
            private String groupId;
            private String labelWhereValue;
            private String logicalCondition;
            private List<SearchLabelParamsReqListBean> searchLabelParamsReqList;

            @Data
            @Builder
            public static class SearchLabelParamsReqListBean {
                /**
                 * paramsId : 5
                 * paramsValue : ["Male"]
                 * paramsOperator :
                 */

                private int paramsId;
                private String paramsOperator;
                private List<String> paramsValue;

            }
        }
    }
}
