
//Used to retreive reimbursement lists on both, manager and employee pages
export interface EmpReimbursements {

    reiId : number;
    rei_amount : number;
    rei_description : string;
    reiStatus: string;
    reiType : string;
    reiAuthor : number;
    rei_resolver : number;
    rei_submitteDate : Date;
    rei_resolvedDate : Date;
    receiptPicName: string;
}
