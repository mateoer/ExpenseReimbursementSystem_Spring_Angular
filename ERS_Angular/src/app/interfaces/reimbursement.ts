
//Used to create new reimbursements on employee page
export interface Reimbursement {
    reiId: number,
    rei_amount: number,
    rei_description: string,    
    reiType: ReiType,
    reiStatus: Status   
}

export enum ReiType {
    LODGING,
    GAS,
    FOOD,
    OTHER
}

export enum Status {
    PENDING,
    APPROVED,
    DENIED
}
