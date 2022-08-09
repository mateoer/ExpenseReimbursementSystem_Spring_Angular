export interface ListEmployeesReimb {
  User(
    userId: number,
    username: string,
    password: string,
    firstName: string,
    lastName: string,
    email: string,
    userRole: string,
    reimbursements: []
  ): {
    reiId: number;
    rei_amount: number;
    rei_submitteDate: Date;
    rei_resolvedDate: Date;
    rei_description: string;
    reiAuthor: number;
    rei_resolver: number;
    reiStatus: string;
    reiType: string;
    reimbursementAuthor: null
  };
}

// export interface Reimb {
//   reiId: number;
//   rei_amount: number;
//   rei_description: string;
//   reiStatus: string;
//   reiType: string;
//   reiAuthor: number;
//   rei_resolver: number;
//   rei_submitteDate: Date;
//   rei_resolvedDate: Date;
// }
