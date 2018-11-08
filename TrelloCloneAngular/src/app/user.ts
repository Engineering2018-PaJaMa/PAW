export class User {
    name: string;
    pass: string;
    id: number;
    constructor(id,username,password){
        this.id=id;
        this.name = username;
        this.pass = password;
    }
}
