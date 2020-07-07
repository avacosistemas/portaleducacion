import { Entity } from "app/modules/fwk/core/model/entity";
import { Role } from "../../role/model/role";
import { Permission } from "../../permission/model/permission";

export class Profile extends Entity {
    name: string;
    enabled: string;
    role: Role;
    permissions: Permission[];
}
