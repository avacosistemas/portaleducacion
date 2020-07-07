import { Entity } from "app/modules/fwk/core/model/entity";

export class Permission extends Entity {
    code: string;
    description: string;
    enabled: string;
}
